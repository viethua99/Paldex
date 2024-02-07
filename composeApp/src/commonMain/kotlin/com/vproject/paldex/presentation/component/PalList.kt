package com.vproject.paldex.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.vproject.paldex.model.Pal
import com.vproject.paldex.model.PalInfo
import com.vproject.paldex.presentation.component.theme.Blue300
import com.vproject.paldex.presentation.component.theme.Blue500
import com.vproject.paldex.presentation.component.theme.Green300
import com.vproject.paldex.presentation.component.theme.Green500
import com.vproject.paldex.presentation.component.theme.Red300
import com.vproject.paldex.presentation.component.theme.Red500
import com.vproject.paldex.presentation.component.theme.Yellow300
import com.vproject.paldex.presentation.component.theme.Yellow500


@Composable
internal fun PalGrid(
    onPalClicked: (name: String) -> Unit,
    palList: List<Pal>,
    isLoading: Boolean,
    loadMoreItems: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = .1f,
        targetValue = .4f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    BoxWithConstraints {
        val columns = when(maxWidth) {
            in 0.dp..349.dp -> 1
            in 350.dp..599.dp -> 2
            in 600.dp..899.dp -> 3
            in 900.dp..1199.dp -> 4
            else -> 5
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(10.dp),
            modifier = modifier,
        ) {
            items(palList, key = { it.name }) { pal ->
                PalItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onPalClicked(pal.name) },
                    pal = pal,
                )
            }

            if (isLoading) {
                items(5) { index ->
                    LaunchedEffect(Unit) {
                        if (index == 0) loadMoreItems()
                    }
                    PalLoadingItem(alpha = alpha)
                }
            }

        }
    }
}


@Composable
private fun PalItem(
    modifier: Modifier = Modifier,
    pal: Pal,
    onClick: () -> Unit
) {
    val brush = remember {
        Brush.linearGradient(
            listOf(
                listOf(
                    Red300,
                    Red500,
                ),
                listOf(
                    Yellow300,
                    Yellow500,
                ),
                listOf(
                    Green300,
                    Green500,
                ),
                listOf(
                    Blue300,
                    Blue500,
                ),
            ).random()
        )
    }

    Card(
        modifier = modifier.clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(brush = brush, alpha = .4f)
                .padding(10.dp)
        ) {
            AsyncImage(
                imageUrl = pal.imageWiki,
                contentDescription = pal.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .fillMaxHeight()
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = pal.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.alpha(.8f)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "#${pal.key}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alpha(.4f)
            )
        }
    }
}