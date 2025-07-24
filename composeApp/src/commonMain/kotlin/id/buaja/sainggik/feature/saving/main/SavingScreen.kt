package id.buaja.sainggik.feature.saving.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import id.buaja.sainggik.feature.saving.main.intent.OnSavingIntent
import id.buaja.sainggik.feature.saving.main.intent.SavingIntent

@Composable
internal fun SavingScreen(
    onSavingIntent: OnSavingIntent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Saving"
        )

        Button(
            onClick = {
                onSavingIntent.invoke(SavingIntent.OpenDetail(10))
            }
        ) {
            Text(
                text = "Detail"
            )
        }
    }
}