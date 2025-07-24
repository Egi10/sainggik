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

val SainggikIcon.Back: ImageVector
    get() {
        if (_back != null) {
            return _back!!
        }
        _back = Builder(name = "IcBack", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(21.0f, 12.0f)
                    curveTo(21.0f, 12.414f, 20.664f, 12.75f, 20.25f, 12.75f)
                    horizontalLineTo(5.56f)
                    lineTo(11.031f, 18.219f)
                    curveTo(11.324f, 18.512f, 11.324f, 18.988f, 11.031f, 19.281f)
                    curveTo(10.738f, 19.574f, 10.262f, 19.574f, 9.969f, 19.281f)
                    lineTo(3.219f, 12.531f)
                    curveTo(3.079f, 12.39f, 2.999f, 12.199f, 2.999f, 12.0f)
                    curveTo(2.999f, 11.801f, 3.079f, 11.61f, 3.219f, 11.469f)
                    lineTo(9.969f, 4.719f)
                    curveTo(10.262f, 4.426f, 10.738f, 4.426f, 11.031f, 4.719f)
                    curveTo(11.324f, 5.012f, 11.324f, 5.488f, 11.031f, 5.781f)
                    lineTo(5.56f, 11.25f)
                    horizontalLineTo(20.25f)
                    curveTo(20.664f, 11.25f, 21.0f, 11.586f, 21.0f, 12.0f)
                    close()
                }
            }
        }
        .build()
        return _back!!
    }

private var _back: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Back, contentDescription = "")
    }
}
