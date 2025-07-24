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

val SainggikIcon.Transactions: ImageVector
    get() {
        if (_transactions != null) {
            return _transactions!!
        }
        _transactions = Builder(name = "Transactions", defaultWidth = 25.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(5.5f, 12.0f)
                    curveTo(5.5f, 12.828f, 4.828f, 13.5f, 4.0f, 13.5f)
                    curveTo(3.172f, 13.5f, 2.5f, 12.828f, 2.5f, 12.0f)
                    curveTo(2.5f, 11.172f, 3.172f, 10.5f, 4.0f, 10.5f)
                    curveTo(4.828f, 10.5f, 5.5f, 11.172f, 5.5f, 12.0f)
                    close()
                    moveTo(4.0f, 4.5f)
                    curveTo(3.172f, 4.5f, 2.5f, 5.172f, 2.5f, 6.0f)
                    curveTo(2.5f, 6.828f, 3.172f, 7.5f, 4.0f, 7.5f)
                    curveTo(4.828f, 7.5f, 5.5f, 6.828f, 5.5f, 6.0f)
                    curveTo(5.5f, 5.172f, 4.828f, 4.5f, 4.0f, 4.5f)
                    close()
                    moveTo(4.0f, 16.5f)
                    curveTo(3.172f, 16.5f, 2.5f, 17.172f, 2.5f, 18.0f)
                    curveTo(2.5f, 18.828f, 3.172f, 19.5f, 4.0f, 19.5f)
                    curveTo(4.828f, 19.5f, 5.5f, 18.828f, 5.5f, 18.0f)
                    curveTo(5.5f, 17.172f, 4.828f, 16.5f, 4.0f, 16.5f)
                    close()
                    moveTo(20.5f, 10.5f)
                    horizontalLineTo(8.5f)
                    curveTo(8.086f, 10.5f, 7.75f, 10.836f, 7.75f, 11.25f)
                    verticalLineTo(12.75f)
                    curveTo(7.75f, 13.164f, 8.086f, 13.5f, 8.5f, 13.5f)
                    horizontalLineTo(20.5f)
                    curveTo(20.914f, 13.5f, 21.25f, 13.164f, 21.25f, 12.75f)
                    verticalLineTo(11.25f)
                    curveTo(21.25f, 10.836f, 20.914f, 10.5f, 20.5f, 10.5f)
                    close()
                    moveTo(20.5f, 4.5f)
                    horizontalLineTo(8.5f)
                    curveTo(8.086f, 4.5f, 7.75f, 4.836f, 7.75f, 5.25f)
                    verticalLineTo(6.75f)
                    curveTo(7.75f, 7.164f, 8.086f, 7.5f, 8.5f, 7.5f)
                    horizontalLineTo(20.5f)
                    curveTo(20.914f, 7.5f, 21.25f, 7.164f, 21.25f, 6.75f)
                    verticalLineTo(5.25f)
                    curveTo(21.25f, 4.836f, 20.914f, 4.5f, 20.5f, 4.5f)
                    close()
                    moveTo(20.5f, 16.5f)
                    horizontalLineTo(8.5f)
                    curveTo(8.086f, 16.5f, 7.75f, 16.836f, 7.75f, 17.25f)
                    verticalLineTo(18.75f)
                    curveTo(7.75f, 19.164f, 8.086f, 19.5f, 8.5f, 19.5f)
                    horizontalLineTo(20.5f)
                    curveTo(20.914f, 19.5f, 21.25f, 19.164f, 21.25f, 18.75f)
                    verticalLineTo(17.25f)
                    curveTo(21.25f, 16.836f, 20.914f, 16.5f, 20.5f, 16.5f)
                    close()
                }
            }
        }
        .build()
        return _transactions!!
    }

private var _transactions: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Transactions, contentDescription = "")
    }
}
