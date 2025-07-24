package id.buaja.sainggik.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

@Immutable
data class SainggikShapes(
    val small: Shape,
    val medium: Shape,
    val large: Shape
)

val LocalSainggikShapes = staticCompositionLocalOf {
    SainggikShapes(
        small = RoundedCornerShape(ZeroCornerSize),
        medium = RoundedCornerShape(ZeroCornerSize),
        large = RoundedCornerShape(ZeroCornerSize)
    )
}