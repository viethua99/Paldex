package com.vproject.paldex.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vproject.paldex.model.Type
import com.vproject.paldex.presentation.screen.detail.PalAbilityUtils

@Composable
internal fun ElementRow(
    types: List<Type>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(types, key = { it.value }) { type ->
            ElementItem(
                name = type.value,
                containerColor = PalAbilityUtils.getElementColor(type.value)
            )
        }
    }
}