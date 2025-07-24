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

val SainggikIcon.Saving: ImageVector
    get() {
        if (_saving != null) {
            return _saving!!
        }
        _saving = Builder(name = "Saving", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF4F7A96)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(15.0f, 11.25f)
                    horizontalLineTo(13.5f)
                    verticalLineTo(5.25f)
                    horizontalLineTo(14.25f)
                    curveTo(15.907f, 5.25f, 17.25f, 6.593f, 17.25f, 8.25f)
                    curveTo(17.25f, 8.664f, 17.586f, 9.0f, 18.0f, 9.0f)
                    curveTo(18.414f, 9.0f, 18.75f, 8.664f, 18.75f, 8.25f)
                    curveTo(18.747f, 5.766f, 16.734f, 3.753f, 14.25f, 3.75f)
                    horizontalLineTo(13.5f)
                    verticalLineTo(2.25f)
                    curveTo(13.5f, 1.836f, 13.164f, 1.5f, 12.75f, 1.5f)
                    curveTo(12.336f, 1.5f, 12.0f, 1.836f, 12.0f, 2.25f)
                    verticalLineTo(3.75f)
                    horizontalLineTo(11.25f)
                    curveTo(8.765f, 3.75f, 6.75f, 5.765f, 6.75f, 8.25f)
                    curveTo(6.75f, 10.735f, 8.765f, 12.75f, 11.25f, 12.75f)
                    horizontalLineTo(12.0f)
                    verticalLineTo(18.75f)
                    horizontalLineTo(10.5f)
                    curveTo(8.843f, 18.75f, 7.5f, 17.407f, 7.5f, 15.75f)
                    curveTo(7.5f, 15.336f, 7.164f, 15.0f, 6.75f, 15.0f)
                    curveTo(6.336f, 15.0f, 6.0f, 15.336f, 6.0f, 15.75f)
                    curveTo(6.003f, 18.234f, 8.016f, 20.247f, 10.5f, 20.25f)
                    horizontalLineTo(12.0f)
                    verticalLineTo(21.75f)
                    curveTo(12.0f, 22.164f, 12.336f, 22.5f, 12.75f, 22.5f)
                    curveTo(13.164f, 22.5f, 13.5f, 22.164f, 13.5f, 21.75f)
                    verticalLineTo(20.25f)
                    horizontalLineTo(15.0f)
                    curveTo(17.485f, 20.25f, 19.5f, 18.235f, 19.5f, 15.75f)
                    curveTo(19.5f, 13.265f, 17.485f, 11.25f, 15.0f, 11.25f)
                    close()
                    moveTo(11.25f, 11.25f)
                    curveTo(9.593f, 11.25f, 8.25f, 9.907f, 8.25f, 8.25f)
                    curveTo(8.25f, 6.593f, 9.593f, 5.25f, 11.25f, 5.25f)
                    horizontalLineTo(12.0f)
                    verticalLineTo(11.25f)
                    horizontalLineTo(11.25f)
                    close()
                    moveTo(15.0f, 18.75f)
                    horizontalLineTo(13.5f)
                    verticalLineTo(12.75f)
                    horizontalLineTo(15.0f)
                    curveTo(16.657f, 12.75f, 18.0f, 14.093f, 18.0f, 15.75f)
                    curveTo(18.0f, 17.407f, 16.657f, 18.75f, 15.0f, 18.75f)
                    close()
                }
            }
        }
        .build()
        return _saving!!
    }

private var _saving: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Saving, contentDescription = "")
    }
}
