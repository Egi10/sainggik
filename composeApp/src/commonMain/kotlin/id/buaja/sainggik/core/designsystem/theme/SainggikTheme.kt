package id.buaja.sainggik.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SainggikTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val sainggikColors = if (!darkTheme) {
        SainggikColors(
            background = Color(0xFFF9FAFB),
            surface = Color(0xFFFFFFFF),
            onBackground = Color(0xFF1E293B),
            onSurfaceVariant = Color(0xFF6B7280),
            primary = Color(0xFF0EA5E9),
            onPrimary = Color(0xFFFFFFFF),
            surfaceVariant = Color(0xFFF1F5F9),
            outline = Color(0xFFE5E7EB),
            error = Color(0xFFEF4444),
            tertiary = Color(0xFF10B981),
            secondary = Color(0xFF64748B),
            primaryContainer = Color(0xFFE0F2FE),
            onPrimaryContainer = Color(0xFF1E293B)
        )
    } else {
        SainggikColors(
            background = Color(0xFF0F172A),
            surface = Color(0xFF1E293B),
            onBackground = Color(0xFFF8FAFC),
            onSurfaceVariant = Color(0xFF9CA3AF),
            primary = Color(0xFF38BDF8),
            onPrimary = Color(0xFF0F172A),
            surfaceVariant = Color(0xFF334155),
            outline = Color(0xFF475569),
            error = Color(0xFFF87171),
            tertiary = Color(0xFF34D399),
            secondary = Color(0xFF94A3B8),
            primaryContainer = Color(0xFF0C4A6E),
            onPrimaryContainer = Color(0xFFE0F2FE)
        )
    }

    val sainggikTypography = SainggikTypography(
        headlineLarge = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = interFamily(),
            lineHeight = 40.sp
        ),
        headlineMedium = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = interFamily(),
            lineHeight = 30.sp
        ),
        headline = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = interFamily(),
            lineHeight = 28.sp
        ),
        title = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = interFamily(),
            lineHeight = 23.sp
        ),
        body = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = interFamily(),
            lineHeight = 24.sp
        ),
        label = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = interFamily(),
            lineHeight = 21.sp
        ),
        caption = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = interFamily(),
            lineHeight = 18.sp
        )
    )

    val sainggikShapes = SainggikShapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(16.dp)
    )

    CompositionLocalProvider(
        LocalSainggikColors provides sainggikColors,
        LocalSainggikTypography provides sainggikTypography,
        LocalSainggikShapes provides sainggikShapes,
        content = content
    )
}

object SainggikTheme {
    val colors: SainggikColors
        @Composable
        get() = LocalSainggikColors.current
    val typography: SainggikTypography
        @Composable
        get() = LocalSainggikTypography.current
    val shapes: SainggikShapes
        @Composable
        get() = LocalSainggikShapes.current
}