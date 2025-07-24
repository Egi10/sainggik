package id.buaja.sainggik.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SainggikButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enable,
        colors = ButtonDefaults.buttonColors(
            containerColor = SainggikTheme.colors.primary,
            contentColor = SainggikTheme.colors.onPrimary,
            disabledContentColor = SainggikTheme.colors.onPrimaryContainer,
            disabledContainerColor = SainggikTheme.colors.primaryContainer
        ),
        contentPadding = PaddingValues(
            all = 12.dp
        ),
        content = {
            Text(
                text = text,
                style = SainggikTheme.typography.body,
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@Preview
@Composable
private fun SainggikButtonPreview() {
    SainggikTheme(
        darkTheme = false
    ) {
        Box(
            modifier = Modifier
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                )
        ) {
            SainggikButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Save",
                onClick = {

                }
            )
        }
    }
}

@Preview
@Composable
private fun SainggikButtonDarkPreview() {
    SainggikTheme(
        darkTheme = true
    ) {
        Box(
            modifier = Modifier
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                )
        ) {
            SainggikButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Save",
                onClick = {

                }
            )
        }
    }
}