package id.buaja.sainggik.core.designsystem.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme

@Composable
fun Modifier.rippleClickable(
    onClick: () -> Unit,
    rippleColor: Color = SainggikTheme.colors.primary,
    role: Role = Role.Button,
    bounded: Boolean = true,
    enabled: Boolean = true,
    radius: Dp = Dp.Unspecified,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    return this.clickable(
        enabled = enabled,
        indication = ripple(
            bounded = bounded,
            color = rippleColor,
            radius = radius
        ),
        interactionSource = interactionSource,
        role = role,
        onClick = onClick
    )
}