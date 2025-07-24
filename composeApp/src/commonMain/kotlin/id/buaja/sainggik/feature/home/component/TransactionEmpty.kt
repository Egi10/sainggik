package id.buaja.sainggik.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun TransactionEmpty(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    colorBorder: Color = SainggikTheme.colors.secondary,
    shape: Shape = SainggikTheme.shapes.medium
) {
    Box(
        modifier = modifier
            .clip(
                shape = shape
            )
            .then(
                Modifier.drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val dashLength = 5.dp.toPx()
                    val gapLength = 5.dp.toPx()

                    // Buat outline path dari shape yang sama
                    val outline = shape.createOutline(
                        size = size,
                        layoutDirection = layoutDirection,
                        density = this
                    )

                    // Convert outline ke path
                    val path = when (outline) {
                        is Outline.Rectangle -> Path().apply {
                            addRect(outline.rect)
                        }

                        is Outline.Rounded -> Path().apply {
                            addRoundRect(outline.roundRect)
                        }

                        is Outline.Generic -> outline.path
                    }

                    // Gambar path dengan stroke dash
                    drawPath(
                        path = path,
                        color = colorBorder,
                        style = Stroke(
                            width = strokeWidth,
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(dashLength, gapLength),
                                phase = 0f
                            )
                        )
                    )
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 56.dp,
                    horizontal = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp
            )
        ) {
            Text(
                text = title,
                style = SainggikTheme.typography.title,
                textAlign = TextAlign.Center,
                color = SainggikTheme.colors.onBackground
            )

            Text(
                text = description,
                style = SainggikTheme.typography.label,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = SainggikTheme.colors.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun TransactionEmptyPreview() {
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
            TransactionEmpty(
                title = "No Spending Data Available",
                description = "Start tracking your expenses to see a breakdown of your spending."
            )
        }
    }
}