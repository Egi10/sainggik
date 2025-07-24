package id.buaja.sainggik

import androidx.compose.runtime.Composable
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.feature.root.RootRoute

@Composable
fun App() {
    SainggikTheme {
        RootRoute()
    }
}