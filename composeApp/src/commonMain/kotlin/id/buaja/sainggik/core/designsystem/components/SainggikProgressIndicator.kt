package id.buaja.sainggik.core.designsystem.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SainggikProgressIndicator(
    progress: Int,
    modifier: Modifier = Modifier
) {
    val targetProgress = (progress.coerceIn(0, 100)) / 100f

    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        ), label = "ProgressAnimation"
    )

    val progressColor = if (animatedProgress > 0.7f) {
        SainggikTheme.colors.error
    } else {
        SainggikTheme.colors.primary
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp
        )
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(
                    height = 4.dp
                )
                .background(
                    color = SainggikTheme.colors.surfaceVariant,
                    shape = CircleShape
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = animatedProgress)
                    .fillMaxHeight()
                    .background(
                        color = progressColor,
                        shape = CircleShape
                    )
            )
        }

        Text(
            text = "$progress%",
            style = SainggikTheme.typography.label,
            fontWeight = FontWeight.Medium,
            color = SainggikTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
private fun SainggikProgressIndicatorPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
                .padding(
                    all = 16.dp
                )
        ) {
            SainggikProgressIndicator(
                progress = 40
            )
        }

    }
}