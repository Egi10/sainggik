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

val SainggikIcon.Calendar: ImageVector
    get() {
        if (_calendar != null) {
            return _calendar!!
        }
        _calendar = Builder(name = "Calendar", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF6B7582)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(19.5f, 3.0f)
                    horizontalLineTo(17.25f)
                    verticalLineTo(2.25f)
                    curveTo(17.25f, 1.836f, 16.914f, 1.5f, 16.5f, 1.5f)
                    curveTo(16.086f, 1.5f, 15.75f, 1.836f, 15.75f, 2.25f)
                    verticalLineTo(3.0f)
                    horizontalLineTo(8.25f)
                    verticalLineTo(2.25f)
                    curveTo(8.25f, 1.836f, 7.914f, 1.5f, 7.5f, 1.5f)
                    curveTo(7.086f, 1.5f, 6.75f, 1.836f, 6.75f, 2.25f)
                    verticalLineTo(3.0f)
                    horizontalLineTo(4.5f)
                    curveTo(3.672f, 3.0f, 3.0f, 3.672f, 3.0f, 4.5f)
                    verticalLineTo(19.5f)
                    curveTo(3.0f, 20.328f, 3.672f, 21.0f, 4.5f, 21.0f)
                    horizontalLineTo(19.5f)
                    curveTo(20.328f, 21.0f, 21.0f, 20.328f, 21.0f, 19.5f)
                    verticalLineTo(4.5f)
                    curveTo(21.0f, 3.672f, 20.328f, 3.0f, 19.5f, 3.0f)
                    verticalLineTo(3.0f)
                    close()
                    moveTo(6.75f, 4.5f)
                    verticalLineTo(5.25f)
                    curveTo(6.75f, 5.664f, 7.086f, 6.0f, 7.5f, 6.0f)
                    curveTo(7.914f, 6.0f, 8.25f, 5.664f, 8.25f, 5.25f)
                    verticalLineTo(4.5f)
                    horizontalLineTo(15.75f)
                    verticalLineTo(5.25f)
                    curveTo(15.75f, 5.664f, 16.086f, 6.0f, 16.5f, 6.0f)
                    curveTo(16.914f, 6.0f, 17.25f, 5.664f, 17.25f, 5.25f)
                    verticalLineTo(4.5f)
                    horizontalLineTo(19.5f)
                    verticalLineTo(7.5f)
                    horizontalLineTo(4.5f)
                    verticalLineTo(4.5f)
                    horizontalLineTo(6.75f)
                    close()
                    moveTo(19.5f, 19.5f)
                    horizontalLineTo(4.5f)
                    verticalLineTo(9.0f)
                    horizontalLineTo(19.5f)
                    verticalLineTo(19.5f)
                    verticalLineTo(19.5f)
                    close()
                    moveTo(10.5f, 11.25f)
                    verticalLineTo(17.25f)
                    curveTo(10.5f, 17.664f, 10.164f, 18.0f, 9.75f, 18.0f)
                    curveTo(9.336f, 18.0f, 9.0f, 17.664f, 9.0f, 17.25f)
                    verticalLineTo(12.463f)
                    lineTo(8.586f, 12.671f)
                    curveTo(8.215f, 12.857f, 7.764f, 12.706f, 7.579f, 12.336f)
                    curveTo(7.393f, 11.965f, 7.544f, 11.514f, 7.914f, 11.329f)
                    lineTo(9.414f, 10.579f)
                    curveTo(9.647f, 10.462f, 9.923f, 10.475f, 10.144f, 10.612f)
                    curveTo(10.366f, 10.748f, 10.5f, 10.99f, 10.5f, 11.25f)
                    verticalLineTo(11.25f)
                    close()
                    moveTo(16.046f, 14.105f)
                    lineTo(14.25f, 16.5f)
                    horizontalLineTo(15.75f)
                    curveTo(16.164f, 16.5f, 16.5f, 16.836f, 16.5f, 17.25f)
                    curveTo(16.5f, 17.664f, 16.164f, 18.0f, 15.75f, 18.0f)
                    horizontalLineTo(12.75f)
                    curveTo(12.466f, 18.0f, 12.206f, 17.84f, 12.079f, 17.585f)
                    curveTo(11.952f, 17.331f, 11.98f, 17.027f, 12.15f, 16.8f)
                    lineTo(14.848f, 13.203f)
                    curveTo(15.015f, 12.98f, 15.045f, 12.683f, 14.926f, 12.432f)
                    curveTo(14.807f, 12.18f, 14.559f, 12.015f, 14.28f, 12.003f)
                    curveTo(14.002f, 11.991f, 13.74f, 12.134f, 13.6f, 12.375f)
                    curveTo(13.47f, 12.615f, 13.22f, 12.765f, 12.948f, 12.767f)
                    curveTo(12.675f, 12.769f, 12.423f, 12.624f, 12.288f, 12.386f)
                    curveTo(12.154f, 12.149f, 12.159f, 11.857f, 12.302f, 11.625f)
                    curveTo(12.811f, 10.743f, 13.849f, 10.314f, 14.832f, 10.577f)
                    curveTo(15.816f, 10.841f, 16.5f, 11.732f, 16.5f, 12.75f)
                    curveTo(16.502f, 13.239f, 16.342f, 13.715f, 16.046f, 14.105f)
                    verticalLineTo(14.105f)
                    close()
                }
            }
        }
        .build()
        return _calendar!!
    }

private var _calendar: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Calendar, contentDescription = "")
    }
}
