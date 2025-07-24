package id.buaja.sainggik.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class SainggikColors(
    val background: Color,
    val surface: Color,
    val onBackground: Color,
    val onSurfaceVariant: Color,
    val primary: Color,
    val onPrimary: Color,
    val surfaceVariant: Color,
    val outline: Color,
    val error: Color,
    val tertiary: Color,
    val secondary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
)

val LocalSainggikColors = staticCompositionLocalOf {
    SainggikColors(
        background = Color.Unspecified,
        surface = Color.Unspecified,
        onBackground = Color.Unspecified,
        onSurfaceVariant = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        surfaceVariant = Color.Unspecified,
        outline = Color.Unspecified,
        error = Color.Unspecified,
        tertiary = Color.Unspecified,
        secondary = Color.Unspecified,
        primaryContainer = Color.Unspecified,
        onPrimaryContainer = Color.Unspecified,
    )
}
