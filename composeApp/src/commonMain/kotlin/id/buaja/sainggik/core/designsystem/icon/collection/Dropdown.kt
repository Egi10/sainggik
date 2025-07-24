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

val SainggikIcon.Dropdown: ImageVector
    get() {
        if (_dropdown != null) {
            return _dropdown!!
        }
        _dropdown = Builder(name = "Dropdown", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF9EB0BF)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(17.031f, 15.969f)
                    curveTo(17.171f, 16.11f, 17.251f, 16.301f, 17.251f, 16.5f)
                    curveTo(17.251f, 16.699f, 17.171f, 16.89f, 17.031f, 17.031f)
                    lineTo(12.531f, 21.531f)
                    curveTo(12.39f, 21.671f, 12.199f, 21.751f, 12.0f, 21.751f)
                    curveTo(11.801f, 21.751f, 11.61f, 21.671f, 11.469f, 21.531f)
                    lineTo(6.969f, 17.031f)
                    curveTo(6.676f, 16.738f, 6.676f, 16.262f, 6.969f, 15.969f)
                    curveTo(7.262f, 15.676f, 7.738f, 15.676f, 8.031f, 15.969f)
                    lineTo(12.0f, 19.94f)
                    lineTo(15.969f, 15.969f)
                    curveTo(16.11f, 15.828f, 16.301f, 15.749f, 16.5f, 15.749f)
                    curveTo(16.699f, 15.749f, 16.89f, 15.828f, 17.031f, 15.969f)
                    close()
                    moveTo(8.031f, 8.031f)
                    lineTo(12.0f, 4.06f)
                    lineTo(15.969f, 8.031f)
                    curveTo(16.262f, 8.324f, 16.738f, 8.324f, 17.031f, 8.031f)
                    curveTo(17.324f, 7.738f, 17.324f, 7.262f, 17.031f, 6.969f)
                    lineTo(12.531f, 2.469f)
                    curveTo(12.39f, 2.329f, 12.199f, 2.249f, 12.0f, 2.249f)
                    curveTo(11.801f, 2.249f, 11.61f, 2.329f, 11.469f, 2.469f)
                    lineTo(6.969f, 6.969f)
                    curveTo(6.676f, 7.262f, 6.676f, 7.738f, 6.969f, 8.031f)
                    curveTo(7.262f, 8.324f, 7.738f, 8.324f, 8.031f, 8.031f)
                    close()
                }
            }
        }
        .build()
        return _dropdown!!
    }

private var _dropdown: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Dropdown, contentDescription = "")
    }
}
