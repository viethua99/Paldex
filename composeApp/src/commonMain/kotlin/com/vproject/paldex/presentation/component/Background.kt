package com.vproject.paldex.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * The main background for the app.
 *
 * @param modifier Modifier to be applied to the background.
 * @param content The background content.
 */
@Composable
fun PaldexAppBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp,
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
            content()
        }
    }
}