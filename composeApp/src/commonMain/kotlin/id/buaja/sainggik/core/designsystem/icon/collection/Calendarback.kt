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

val SainggikIcon.CalendarBack: ImageVector
    get() {
        if (_calendarback != null) {
            return _calendarback!!
        }
        _calendarback = Builder(name = "Calendarback", defaultWidth = 18.0.dp, defaultHeight =
                18.0.dp, viewportWidth = 18.0f, viewportHeight = 18.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF121417)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(11.648f, 14.227f)
                    curveTo(11.868f, 14.447f, 11.868f, 14.803f, 11.648f, 15.023f)
                    curveTo(11.428f, 15.243f, 11.072f, 15.243f, 10.852f, 15.023f)
                    lineTo(5.227f, 9.398f)
                    curveTo(5.121f, 9.292f, 5.062f, 9.149f, 5.062f, 9.0f)
                    curveTo(5.062f, 8.851f, 5.121f, 8.708f, 5.227f, 8.602f)
                    lineTo(10.852f, 2.977f)
                    curveTo(11.072f, 2.757f, 11.428f, 2.757f, 11.648f, 2.977f)
                    curveTo(11.868f, 3.197f, 11.868f, 3.553f, 11.648f, 3.773f)
                    lineTo(6.42f, 9.0f)
                    lineTo(11.648f, 14.227f)
                    close()
                }
            }
        }
        .build()
        return _calendarback!!
    }

private var _calendarback: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.CalendarBack, contentDescription = "")
    }
}
