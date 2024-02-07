package com.vproject.paldex.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vproject.paldex.model.PalInfo

@Composable
internal fun PalStats(
    palInfo: PalInfo,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 16.dp)
    ) {
        key("hp") {
            PalStatItem(
                modifier = Modifier.padding(vertical = 8.dp),
                statName = "HP",
                statValue = palInfo.stats.hp
            )
        }
        key("melee-attack") {
            PalStatItem(
                modifier = Modifier.padding(vertical = 8.dp),
                statName = "Melee Att",
                statValue = palInfo.stats.attack.melee
            )
        }
        key("ranged-attack") {
            PalStatItem(
                modifier = Modifier.padding(vertical = 8.dp),
                statName = "Ranged Att",
                statValue = palInfo.stats.attack.ranged
            )
        }
        key("defense") {
            PalStatItem(
                modifier = Modifier.padding(vertical = 8.dp),
                statName = "Defense",
                statValue = palInfo.stats.defense
            )
        }
        key("stamina") {
            PalStatItem(
                modifier = Modifier.padding(vertical = 8.dp),
                statName = "Stamina",
                statValue = palInfo.stats.stamina
            )
        }
    }
}