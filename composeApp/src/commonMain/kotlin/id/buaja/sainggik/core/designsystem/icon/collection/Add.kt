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

val SainggikIcon.Add: ImageVector
    get() {
        if (_add != null) {
            return _add!!
        }
        _add = Builder(name = "Add", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp, viewportWidth
                = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFF7FAFC)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(21.0f, 12.0f)
                    curveTo(21.0f, 12.414f, 20.664f, 12.75f, 20.25f, 12.75f)
                    horizontalLineTo(12.75f)
                    verticalLineTo(20.25f)
                    curveTo(12.75f, 20.664f, 12.414f, 21.0f, 12.0f, 21.0f)
                    curveTo(11.586f, 21.0f, 11.25f, 20.664f, 11.25f, 20.25f)
                    verticalLineTo(12.75f)
                    horizontalLineTo(3.75f)
                    curveTo(3.336f, 12.75f, 3.0f, 12.414f, 3.0f, 12.0f)
                    curveTo(3.0f, 11.586f, 3.336f, 11.25f, 3.75f, 11.25f)
                    horizontalLineTo(11.25f)
                    verticalLineTo(3.75f)
                    curveTo(11.25f, 3.336f, 11.586f, 3.0f, 12.0f, 3.0f)
                    curveTo(12.414f, 3.0f, 12.75f, 3.336f, 12.75f, 3.75f)
                    verticalLineTo(11.25f)
                    horizontalLineTo(20.25f)
                    curveTo(20.664f, 11.25f, 21.0f, 11.586f, 21.0f, 12.0f)
                    close()
                }
            }
        }
        .build()
        return _add!!
    }

private var _add: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Add, contentDescription = "")
    }
}
