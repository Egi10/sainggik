package id.buaja.sainggik.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Add
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SainggikFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(
                width = 64.dp,
                height = 56.dp
            )
            .clip(shape = RoundedCornerShape(24.dp))
            .background(
                color = SainggikTheme.colors.primary,
                shape = RoundedCornerShape(24.dp)
            )
            .rippleClickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = SainggikIcon.Add,
            contentDescription = null,
            tint = SainggikTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun SainggikFloatingActionButtonPreview() {
    SainggikTheme {
        SainggikFloatingActionButton(
            onClick = {

            }
        )
    }
}