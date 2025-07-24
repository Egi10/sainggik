package id.buaja.sainggik.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MessageFeedback(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    image: Painter? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (image != null) {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(
                        minHeight = 201.dp
                    )
            )

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )
        }

        Text(
            text = title,
            style = SainggikTheme.typography.title,
            color = SainggikTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        Text(
            text = description,
            style = SainggikTheme.typography.label,
            color = SainggikTheme.colors.onBackground,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun MessageFeedbackPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            MessageFeedback(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "No Transactions Found",
                description = "It appears there are no transactions matching your current filters. Try adjusting your filter settings or adding new transactions to see your financial activity."
            )
        }
    }
}