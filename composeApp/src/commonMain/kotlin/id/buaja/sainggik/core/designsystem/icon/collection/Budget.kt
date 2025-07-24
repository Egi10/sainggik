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

val SainggikIcon.Budget: ImageVector
    get() {
        if (_budget != null) {
            return _budget!!
        }
        _budget = Builder(name = "Budget", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF94B3C7)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(21.75f, 18.75f)
                    horizontalLineTo(21.0f)
                    verticalLineTo(3.75f)
                    curveTo(21.0f, 3.336f, 20.664f, 3.0f, 20.25f, 3.0f)
                    horizontalLineTo(15.0f)
                    curveTo(14.586f, 3.0f, 14.25f, 3.336f, 14.25f, 3.75f)
                    verticalLineTo(7.5f)
                    horizontalLineTo(9.75f)
                    curveTo(9.336f, 7.5f, 9.0f, 7.836f, 9.0f, 8.25f)
                    verticalLineTo(12.0f)
                    horizontalLineTo(5.25f)
                    curveTo(4.836f, 12.0f, 4.5f, 12.336f, 4.5f, 12.75f)
                    verticalLineTo(18.75f)
                    horizontalLineTo(3.75f)
                    curveTo(3.336f, 18.75f, 3.0f, 19.086f, 3.0f, 19.5f)
                    curveTo(3.0f, 19.914f, 3.336f, 20.25f, 3.75f, 20.25f)
                    horizontalLineTo(21.75f)
                    curveTo(22.164f, 20.25f, 22.5f, 19.914f, 22.5f, 19.5f)
                    curveTo(22.5f, 19.086f, 22.164f, 18.75f, 21.75f, 18.75f)
                    close()
                    moveTo(15.75f, 4.5f)
                    horizontalLineTo(19.5f)
                    verticalLineTo(18.75f)
                    horizontalLineTo(15.75f)
                    verticalLineTo(4.5f)
                    close()
                    moveTo(10.5f, 9.0f)
                    horizontalLineTo(14.25f)
                    verticalLineTo(18.75f)
                    horizontalLineTo(10.5f)
                    verticalLineTo(9.0f)
                    close()
                    moveTo(6.0f, 13.5f)
                    horizontalLineTo(9.0f)
                    verticalLineTo(18.75f)
                    horizontalLineTo(6.0f)
                    verticalLineTo(13.5f)
                    close()
                }
            }
        }
        .build()
        return _budget!!
    }

private var _budget: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Budget, contentDescription = "")
    }
}
