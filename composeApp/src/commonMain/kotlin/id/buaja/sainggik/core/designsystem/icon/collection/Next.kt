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

val SainggikIcon.Next: ImageVector
    get() {
        if (_next != null) {
            return _next!!
        }
        _next = Builder(name = "Next", defaultWidth = 28.0.dp, defaultHeight = 28.0.dp,
                viewportWidth = 28.0f, viewportHeight = 28.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF121417)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(19.031f, 14.531f)
                    lineTo(11.531f, 22.031f)
                    curveTo(11.238f, 22.324f, 10.762f, 22.324f, 10.469f, 22.031f)
                    curveTo(10.176f, 21.738f, 10.176f, 21.262f, 10.469f, 20.969f)
                    lineTo(17.44f, 14.0f)
                    lineTo(10.469f, 7.031f)
                    curveTo(10.176f, 6.738f, 10.176f, 6.262f, 10.469f, 5.969f)
                    curveTo(10.762f, 5.676f, 11.238f, 5.676f, 11.531f, 5.969f)
                    lineTo(19.031f, 13.469f)
                    curveTo(19.171f, 13.61f, 19.251f, 13.801f, 19.251f, 14.0f)
                    curveTo(19.251f, 14.199f, 19.171f, 14.39f, 19.031f, 14.531f)
                    verticalLineTo(14.531f)
                    close()
                }
            }
        }
        .build()
        return _next!!
    }

private var _next: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Next, contentDescription = "")
    }
}
