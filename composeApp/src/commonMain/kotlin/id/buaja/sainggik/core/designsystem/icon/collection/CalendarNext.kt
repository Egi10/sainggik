package id.buaja.sainggik.core.designsystem.icon.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import org.jetbrains.compose.ui.tooling.preview.Preview

val SainggikIcon.CalendarNext: ImageVector
    get() {
        if (_calendarnext != null) {
            return _calendarnext!!
        }
        _calendarnext = Builder(name = "Calendarnext", defaultWidth = 18.0.dp, defaultHeight =
                18.0.dp, viewportWidth = 18.0f, viewportHeight = 18.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF121417)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(12.773f, 9.398f)
                    lineTo(7.148f, 15.023f)
                    curveTo(6.928f, 15.243f, 6.572f, 15.243f, 6.352f, 15.023f)
                    curveTo(6.132f, 14.803f, 6.132f, 14.447f, 6.352f, 14.227f)
                    lineTo(11.58f, 9.0f)
                    lineTo(6.352f, 3.773f)
                    curveTo(6.132f, 3.553f, 6.132f, 3.197f, 6.352f, 2.977f)
                    curveTo(6.572f, 2.757f, 6.928f, 2.757f, 7.148f, 2.977f)
                    lineTo(12.773f, 8.602f)
                    curveTo(12.879f, 8.708f, 12.938f, 8.851f, 12.938f, 9.0f)
                    curveTo(12.938f, 9.149f, 12.879f, 9.292f, 12.773f, 9.398f)
                    close()
                }
            }
        }
        .build()
        return _calendarnext!!
    }

private var _calendarnext: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.CalendarNext, contentDescription = "")
    }
}
