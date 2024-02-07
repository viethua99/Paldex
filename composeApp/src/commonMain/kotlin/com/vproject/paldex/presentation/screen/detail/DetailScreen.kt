package com.vproject.paldex.presentation.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.vproject.paldex.presentation.component.ElementRow
import com.vproject.paldex.presentation.component.AsyncImage
import com.vproject.paldex.presentation.component.CustomIcons
import com.vproject.paldex.presentation.component.LocalSafeArea
import com.vproject.paldex.presentation.component.PalInfos
import com.vproject.paldex.presentation.component.PalStats
import com.vproject.paldex.presentation.component.theme.Dark
import com.vproject.paldex.presentation.component.theme.Dragon
import com.vproject.paldex.presentation.component.theme.Electric
import com.vproject.paldex.presentation.component.theme.Fire
import com.vproject.paldex.presentation.component.theme.Grass
import com.vproject.paldex.presentation.component.theme.Gray400
import com.vproject.paldex.presentation.component.theme.Ground
import com.vproject.paldex.presentation.component.theme.Ice
import com.vproject.paldex.presentation.component.theme.Neutral
import com.vproject.paldex.presentation.component.theme.Water

class DetailScreen(private val name: String) : Screen {
    @Composable
    override fun Content() {
        val screenModel: DetailModel = getScreenModel()
        val detailUiState by screenModel.state.collectAsState()
        val localNavigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            screenModel.getPalDetail(name)
        }

        DetailContent(
            detailUiState,
            onBackArrowClicked = {
                localNavigator?.pop()
            },
            onFavoriteClicked = { name, isFavorite ->
                screenModel.setPalInfoFavorite(name, isFavorite)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailContent(
    detailUiState: DetailUiState,
    onBackArrowClicked: () -> Unit,
    onFavoriteClicked: (name: String, isFavorite: Boolean) -> Unit
) {
    if (detailUiState is DetailUiState.Success) {
        Box(contentAlignment = Alignment.TopCenter) {
            AsyncImage(
                imageUrl = detailUiState.palInfo.imageWiki,
                contentDescription = detailUiState.palInfo.name,
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(3f) }),
                modifier = Modifier
                    .widthIn(max = 800.dp)
                    .fillMaxWidth(.9f)
                    .wrapContentHeight(Alignment.Top, true)
                    .scale(1f, 1.8f)
                    .blur(70.dp, BlurredEdgeTreatment.Unbounded)
                    .alpha(.5f)
            )

            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(
                                onClick = { onBackArrowClicked() }
                            ) {
                                Icon(CustomIcons.ArrowBack, contentDescription = null)
                            }
                        },
                        title = {
                            Text(
                                text = detailUiState.palInfo.name.replaceFirstChar { it.uppercaseChar() },
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    onFavoriteClicked(
                                        detailUiState.palInfo.name,
                                        !detailUiState.palInfo.isFavorite
                                    )
                                }
                            ) {
                                Icon(
                                    if (detailUiState.palInfo.isFavorite) CustomIcons.Favorite else CustomIcons.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = if (detailUiState.palInfo.isFavorite) PalAbilityUtils.getElementColor(detailUiState.palInfo.types[0].value)
                                    else MaterialTheme.colorScheme.onBackground
                                )
                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                },
                containerColor = Color.Transparent,
                modifier = Modifier.padding(LocalSafeArea.current)
            ) { paddingValue ->
                Box(
                    modifier = Modifier
                        .padding(paddingValue)
                        .padding(10.dp)
                ) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item("image") {
                            AsyncImage(
                                imageUrl = detailUiState.palInfo.imageWiki,
                                contentDescription = detailUiState.palInfo.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .widthIn(max = 500.dp)
                                    .fillMaxWidth()
                                    .aspectRatio(1.2f)
                                    .fillMaxHeight()
                            )
                        }

                        item("name") {
                            Text(
                                text = detailUiState.palInfo.name.replaceFirstChar { it.uppercaseChar() },
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.displaySmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item("key") {
                            Text(
                                text = "#${detailUiState.palInfo.key}",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item("elements") {
                            ElementRow(
                                types = detailUiState.palInfo.types
                            )
                        }

                        item("infos") {
                            PalInfos(
                                palInfo = detailUiState.palInfo,
                                modifier = Modifier
                                    .padding(top = 18.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }

                        item("stats") {
                            PalStats(
                                palInfo = detailUiState.palInfo,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }
                    }
                }
            }
        }
    }
}

object PalAbilityUtils {
    fun getElementColor(name: String): Color = when (name) {
        "dark" -> Dark
        "dragon" -> Dragon
        "ground" -> Ground
        "electric" -> Electric
        "fire" -> Fire
        "ice" -> Ice
        "grass" -> Grass
        "neutral" -> Neutral
        "water" -> Water
        else -> Gray400
    }
}