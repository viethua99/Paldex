package com.vproject.paldex.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vproject.paldex.presentation.component.CustomIcons
import com.vproject.paldex.presentation.component.PalGrid
import com.vproject.paldex.presentation.component.PaldexTopBar
import com.vproject.paldex.presentation.screen.detail.DetailScreen

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(CustomIcons.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val parentNavigator = LocalNavigator.current?.parent
        val screenModel: HomeModel = getScreenModel()
        val homeUiState by screenModel.state.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.getPalList()
        }

        HomeContent(
            homeUiState,
            onPalClicked = { name ->
                parentNavigator?.push(DetailScreen(name))
            },
        )
    }
}

@Composable
private fun HomeContent(
    homeUiState: HomeUiState,
    onPalClicked: (name: String) -> Unit
) {
    Column {
        HomeTopBar()

        Text(
            text = "Find Your Pal!",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )

        SearchPal()

        when (homeUiState) {
            is HomeUiState.Initial -> {
                // Initial state
            }
            is HomeUiState.Loading -> {
                // Loading state
            }
            is HomeUiState.Success -> {
                PalGrid(
                    onPalClicked = onPalClicked,
                    palList = homeUiState.palList,
                    isLoading = false,
                    loadMoreItems = {
//                        if (uiState.palList.isEmpty()) return@PalGrid
//
//                        val nextPage = uiState.palList.last(). + 1
//                        onEvent(PokedexStore.Intent.LoadPokemonListByPage(page = nextPage))
                    }
                )
            }
            is HomeUiState.Error -> {
                Text(homeUiState.message)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(modifier: Modifier = Modifier) {
    PaldexTopBar(
        modifier = modifier,
        title = "Paldex",
    )
}

@Composable
private fun SearchPal(
    onPalClicked: (key: String) -> Unit = {}
) {
    val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f)

    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text(text = "Search Pal") },
        leadingIcon = {
            IconButton(onClick = {onPalClicked("")}) {
                Icon(CustomIcons.Search, contentDescription = "Search Pal")
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
        ),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    )
}
