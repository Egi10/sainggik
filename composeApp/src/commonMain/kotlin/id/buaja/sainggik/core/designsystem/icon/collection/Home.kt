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

val SainggikIcon.Home: ImageVector
    get() {
        if (_home != null) {
            return _home!!
        }
        _home = Builder(name = "Home", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF94B3C7)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(21.265f, 9.728f)
                    lineTo(13.765f, 2.652f)
                    curveTo(13.762f, 2.649f, 13.758f, 2.646f, 13.755f, 2.642f)
                    curveTo(13.183f, 2.122f, 12.309f, 2.122f, 11.737f, 2.642f)
                    lineTo(11.726f, 2.652f)
                    lineTo(4.235f, 9.728f)
                    curveTo(3.926f, 10.012f, 3.75f, 10.413f, 3.75f, 10.833f)
                    verticalLineTo(19.5f)
                    curveTo(3.75f, 20.328f, 4.422f, 21.0f, 5.25f, 21.0f)
                    horizontalLineTo(9.75f)
                    curveTo(10.578f, 21.0f, 11.25f, 20.328f, 11.25f, 19.5f)
                    verticalLineTo(15.0f)
                    horizontalLineTo(14.25f)
                    verticalLineTo(19.5f)
                    curveTo(14.25f, 20.328f, 14.922f, 21.0f, 15.75f, 21.0f)
                    horizontalLineTo(20.25f)
                    curveTo(21.078f, 21.0f, 21.75f, 20.328f, 21.75f, 19.5f)
                    verticalLineTo(10.833f)
                    curveTo(21.75f, 10.413f, 21.574f, 10.012f, 21.265f, 9.728f)
                    close()
                    moveTo(20.25f, 19.5f)
                    horizontalLineTo(15.75f)
                    verticalLineTo(15.0f)
                    curveTo(15.75f, 14.172f, 15.078f, 13.5f, 14.25f, 13.5f)
                    horizontalLineTo(11.25f)
                    curveTo(10.422f, 13.5f, 9.75f, 14.172f, 9.75f, 15.0f)
                    verticalLineTo(19.5f)
                    horizontalLineTo(5.25f)
                    verticalLineTo(10.833f)
                    lineTo(5.26f, 10.823f)
                    lineTo(12.75f, 3.75f)
                    lineTo(20.241f, 10.822f)
                    lineTo(20.251f, 10.831f)
                    lineTo(20.25f, 19.5f)
                    close()
                }
            }
        }
        .build()
        return _home!!
    }

private var _home: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Home, contentDescription = "")
    }
}
