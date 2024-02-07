package com.vproject.paldex.presentation.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vproject.paldex.presentation.component.CustomIcons
import com.vproject.paldex.presentation.component.PalGrid
import com.vproject.paldex.presentation.component.PaldexTopBar
import com.vproject.paldex.presentation.screen.detail.DetailScreen
import com.vproject.paldex.presentation.screen.home.HomeUiState

object FavoriteTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(CustomIcons.Favorite)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Favorite",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val parentNavigator = LocalNavigator.current?.parent
        val screenModel = getScreenModel<FavoriteModel>()
        val favoriteUiState by screenModel.state.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.getFavoritePalList()
        }

        FavoriteContent(
            favoriteUiState,
            onPalClicked = { name ->
                parentNavigator?.push(DetailScreen(name))
            }
        )
    }
}

@Composable
private fun FavoriteContent(
    favoriteUiState: FavoriteUiState,
    onPalClicked: (name: String) -> Unit
) {
    Column {
        FavoriteTopBar()

        when (favoriteUiState) {
            is FavoriteUiState.Initial -> {
                // Initial state
            }
            is FavoriteUiState.Loading -> {
                // Loading state
            }
            is FavoriteUiState.Success -> {
                if (favoriteUiState.palList.isEmpty()) {
                    Text("Your favorite list is empty")
                } else {
                    PalGrid(
                        onPalClicked = onPalClicked,
                        palList = favoriteUiState.palList,
                        isLoading = false,
                    )
                }
            }
            is FavoriteUiState.Error -> {
                Text(favoriteUiState.message)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteTopBar(
    modifier: Modifier = Modifier
) {
    PaldexTopBar(modifier = modifier, title = "Favorite")
}
