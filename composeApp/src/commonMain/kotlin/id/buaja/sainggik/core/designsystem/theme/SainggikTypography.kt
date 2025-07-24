package id.buaja.sainggik.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.inter_medium
import sainggik.composeapp.generated.resources.inter_regular
import sainggik.composeapp.generated.resources.inter_semi_bold

@Immutable
data class SainggikTypography(
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headline: TextStyle,
    val title: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
    val caption: TextStyle
)

val LocalSainggikTypography = staticCompositionLocalOf {
    SainggikTypography(
        headlineLarge = TextStyle.Default,
        headlineMedium = TextStyle.Default,
        headline = TextStyle.Default,
        title = TextStyle.Default,
        body = TextStyle.Default,
        label = TextStyle.Default,
        caption = TextStyle.Default
    )
}

@Composable
fun interFamily() = FontFamily(
    fonts = listOf(
        Font(
            resource = Res.font.inter_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.inter_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.inter_semi_bold,
            weight = FontWeight.SemiBold
        )
    )
)
