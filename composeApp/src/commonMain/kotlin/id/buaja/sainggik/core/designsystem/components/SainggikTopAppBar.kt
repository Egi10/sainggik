package id.buaja.sainggik.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Back
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SainggikTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .height(72.dp)
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onBackClick != null) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .rippleClickable(
                            onClick = {
                                onBackClick.invoke()
                            }
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        imageVector = SainggikIcon.Back,
                        contentDescription = null,
                        tint = SainggikTheme.colors.onBackground
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                )
            }


            Text(
                modifier = Modifier
                    .fillMaxWidth(
                        fraction = 0.845f
                    ),
                text = title,
                style = SainggikTheme.typography.title,
                color = SainggikTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun SainggikTopAppBarPreview() {
    SainggikTheme(
        darkTheme = false
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            SainggikTopAppBar(
                title = "New Transaction",
                onBackClick = {

                }
            )
        }
    }
}

@Preview
@Composable
private fun SainggikTopAppBarDarkPreview() {
    SainggikTheme(
        darkTheme = true
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            SainggikTopAppBar(
                title = "New Transaction",
                onBackClick = {

                }
            )
        }
    }
}