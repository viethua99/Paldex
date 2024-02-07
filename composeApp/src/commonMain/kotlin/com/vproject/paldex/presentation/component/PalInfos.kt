package com.vproject.paldex.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.vproject.paldex.model.PalInfo

@Composable
internal fun PalInfos(
    palInfo: PalInfo,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.outline.copy(.2f))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Suitability",
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(4.dp))

            palInfo.suitability.forEach { suitability ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        imageUrl = "http://10.0.2.2:3000${suitability.image}",
//                        imageUrl = "http://localhost:3000${suitability.image}",
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(Modifier.width(4.dp))

                    val type = if (suitability.type == "medicine_production") {
                        "MP"
                    } else if (suitability.type == "generating_electricity") {
                        "GE"
                    } else {
                        suitability.type
                    }.replaceFirstChar { it.uppercaseChar() }
                    Text(
                        text = type,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = suitability.level.toString(),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(60.dp)
                .background(color = MaterialTheme.colorScheme.outline)
                .align(Alignment.CenterVertically)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Information",
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = "Size:",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = palInfo.size.uppercase(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Row {
                Text(
                    text = "Rarity:",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = palInfo.rarity.toString().replaceFirstChar { it.uppercaseChar() },
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Row {
                Text(
                    text = "Price:",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = palInfo.price.toString().replaceFirstChar { it.uppercaseChar() },
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Row {
                Text(
                    text = "Genus:",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = palInfo.genus.replaceFirstChar { it.uppercaseChar() },
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}