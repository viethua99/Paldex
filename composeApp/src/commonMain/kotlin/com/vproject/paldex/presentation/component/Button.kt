package com.vproject.paldex.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Custom filled button with generic content slot. Wraps Material 3 [Button].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param contentPadding The spacing values to apply internally between the container and the
 * content.
 * @param content The button content.
 */
@Composable
fun CustomFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        contentPadding = contentPadding,
        content = content,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

/**
 * Custom filled button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 */
@Composable
fun CustomFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val contentPadding =
        if (leadingIcon != null) ButtonDefaults.ButtonWithIconContentPadding else ButtonDefaults.ContentPadding
    CustomFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding
    ) {
        CustomButtonContent(text = text, leadingIcon = leadingIcon)
    }
}

/**
 * Internal custom button content layout for arranging the text label and leading icon.
 *
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Default is `null` for no leading icon.
 */
@Composable
private fun CustomButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    leadingIcon?.let { nonNullLeadingIcon ->
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            nonNullLeadingIcon()
        }
    }

    val leadingIconStartPadding = if (leadingIcon != null) ButtonDefaults.IconSpacing else 0.dp
    Box(Modifier.padding(start = leadingIconStartPadding)) {
        text()
    }
}