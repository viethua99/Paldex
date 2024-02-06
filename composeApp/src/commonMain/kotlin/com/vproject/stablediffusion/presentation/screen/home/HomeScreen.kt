package com.vproject.stablediffusion.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.presentation.component.AsyncImage
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.StableDiffusionTopBar
import com.vproject.stablediffusion.presentation.screen.detail.DetailScreen
import com.vproject.stablediffusion.presentation.screen.generate.GenerateScreen
import com.vproject.stablediffusion.presentation.screen.recent.RecentModel
import dev.icerock.moko.resources.compose.painterResource

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
        val screenModel = getScreenModel<RecentModel>()
        HomeContent(
            onStableDiffusionModeClicked = { stableDiffusionMode ->
                parentNavigator?.push(GenerateScreen(stableDiffusionMode))
            },
            onShowcaseClicked = {
                parentNavigator?.push(DetailScreen())
            }
        )
    }
}

@Composable
private fun HomeContent(
    onStableDiffusionModeClicked: (stableDiffusionMode: StableDiffusionMode) -> Unit = {},
    onShowcaseClicked: () -> Unit = {},
    ) {
    Column {
        HomeTopBar()
        Column(
            modifier = Modifier.padding(10.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            AiCreateSection(onStableDiffusionModeClicked = onStableDiffusionModeClicked)
            TextToImageSection(onShowcaseClicked = onShowcaseClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(modifier: Modifier = Modifier) {
    StableDiffusionTopBar(
        modifier = modifier,
        title = "KMP Stable Diffusion",
    )
}

@Composable
private fun SectionHeader(
    title: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    isSeeMoreEnabled: Boolean = false,
    onSeeMoreClicked: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        leadingIcon?.let { nonNullLeadingIcon ->
            nonNullLeadingIcon()
        }

        val leadingIconStartPadding = if (leadingIcon != null) ButtonDefaults.IconSpacing else 0.dp
        Box(Modifier.padding(start = leadingIconStartPadding)) {
            Text(
                title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
            )
        }

        if (isSeeMoreEnabled) {
            Spacer(
                Modifier.weight(1f).fillMaxHeight()
            )

            Text(
                "See more >",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                ),
            )
        }
    }
}

@Composable
private fun AiCreateSection(
    onStableDiffusionModeClicked: (stableDiffusionMode: StableDiffusionMode) -> Unit = {}
) {
    SectionHeader("AI Creation", leadingIcon = {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            imageVector = CustomIcons.Home,
            contentDescription = null
        )
    })
    Spacer(Modifier.height(10.dp))
    AiCreationModeList(
        modifier = Modifier.fillMaxWidth(),
        modeList = StableDiffusionMode.entries,
        onStableDiffusionModeClicked = onStableDiffusionModeClicked
    )
    Spacer(Modifier.height(10.dp))
}

@Composable
private fun AiCreationModeList(
    modifier: Modifier = Modifier,
    modeList: List<StableDiffusionMode>,
    onStableDiffusionModeClicked: (stableDiffusionMode: StableDiffusionMode) -> Unit
) {
    val lazyGridState = rememberLazyGridState()
    LazyHorizontalGrid(
        modifier = modifier.height(360.dp),
        state = lazyGridState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(
            items = modeList,
            key = { it },
        ) { creationMode ->
            AiCreationModeItem(
                modifier = Modifier.width(270.dp),
                stableDiffusionMode = creationMode,
                onItemClicked = onStableDiffusionModeClicked
            )
        }
    }
}

@Composable
private fun AiCreationModeItem(
    modifier: Modifier = Modifier,
    stableDiffusionMode: StableDiffusionMode,
    onItemClicked: (stableDiffusionMode: StableDiffusionMode) -> Unit = {}
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.DarkGray)
            .clickable {
                onItemClicked(stableDiffusionMode)
            }
    ) {
        Image(
            painterResource(stableDiffusionMode.imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
                .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurface,
                imageVector = CustomIcons.Home,
                contentDescription = null
            )
            Spacer(Modifier.width(5.dp).fillMaxHeight())
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                text = stableDiffusionMode.title,
                maxLines = 1
            )
            Spacer(Modifier.weight(1f).fillMaxHeight())
            IconButton(
                onClick = {},
                modifier = modifier
                    .size(24.dp).weight(0.3f)
            ) {
                Icon(
                    imageVector = CustomIcons.FilledFavorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .shadow(
                            elevation = 1.dp,
                            shape = CircleShape
                        )
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = CircleShape
                        )
                )
            }
        }

        Text(
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            modifier = Modifier.fillMaxWidth()
                .weight(1.5f)
                .padding(vertical = 5.dp, horizontal = 10.dp),
            text = stableDiffusionMode.explanation,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun TextToImageSection(
    onShowcaseClicked: () -> Unit,
    ) {
    SectionHeader(StableDiffusionMode.TEXT_TO_IMAGE.title, leadingIcon = {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            imageVector = CustomIcons.Home,
            contentDescription = null
        )
    }, isSeeMoreEnabled = true)
    ShowcaseList(
        showcaseList = listOf(
            Showcase(
                "0",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
            Showcase(
                "1",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
            Showcase(
                "2",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
            Showcase(
                "3",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
            Showcase(
                "4",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
            Showcase(
                "5",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
            Showcase(
                "6",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
            Showcase(
                "7",
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png"
            ),
        ),
        onShowcaseClicked = onShowcaseClicked,
    )
}

@Composable
private fun ShowcaseList(
    modifier: Modifier = Modifier,
    showcaseList: List<Showcase>,
    onShowcaseClicked: () -> Unit,
) {
    val lazyGridState = rememberLazyGridState()
    LazyHorizontalGrid(
        modifier = modifier.height(200.dp),
        state = lazyGridState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(
            items = showcaseList,
            key = { it.id },
        ) { showcase ->
            ShowcaseItem(
                modifier = Modifier.width(150.dp),
                showcase = showcase,
                onItemClicked = onShowcaseClicked
            )
        }
    }
}

@Composable
private fun ShowcaseItem(
    modifier: Modifier = Modifier,
    showcase: Showcase,
    onItemClicked: () -> Unit = {}
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                onItemClicked()
            }
    ) {
        AsyncImage(
            imageUrl = showcase.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
        )
    }
}

data class Showcase(val id: String, val imageUrl: String)
