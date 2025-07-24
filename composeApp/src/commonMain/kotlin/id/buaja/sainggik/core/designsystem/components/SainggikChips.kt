package id.buaja.sainggik.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SainggikChips(
    selected: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .semantics {
                role = Role.Checkbox
            }
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = if (selected) {
                    SainggikTheme.colors.primary
                } else {
                    SainggikTheme.colors.surfaceVariant
                },
                shape = RoundedCornerShape(16.dp)
            )
            .rippleClickable(
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier
                .padding(
                    vertical = 6.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                text = label,
                style = SainggikTheme.typography.label,
                color = if (selected) {
                    SainggikTheme.colors.onPrimary
                } else {
                    SainggikTheme.colors.onBackground
                }
            )
        }
    }
}

@Preview
@Composable
private fun SainggikChipsPreview() {
    SainggikTheme {
        Row(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                ),
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp
            )
        ) {
            SainggikChips(
                selected = false,
                label = "Today",
                onClick = {

                }
            )

            SainggikChips(
                selected = true,
                label = "Today",
                onClick = {

                }
            )
        }
    }
}