package id.buaja.sainggik.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SplashRoute(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    SplashScreen(
        onFinish = onFinish,
        modifier = modifier
    )
}