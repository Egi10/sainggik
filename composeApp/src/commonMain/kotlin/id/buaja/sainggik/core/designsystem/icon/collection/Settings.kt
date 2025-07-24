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

val SainggikIcon.Settings: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }
        _settings = Builder(name = "Settings", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF94B3C7)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd) {
                    moveTo(12.25f, 7.5f)
                    curveTo(9.765f, 7.5f, 7.75f, 9.515f, 7.75f, 12.0f)
                    curveTo(7.75f, 14.485f, 9.765f, 16.5f, 12.25f, 16.5f)
                    curveTo(14.735f, 16.5f, 16.75f, 14.485f, 16.75f, 12.0f)
                    curveTo(16.747f, 9.516f, 14.734f, 7.503f, 12.25f, 7.5f)
                    close()
                    moveTo(12.25f, 15.0f)
                    curveTo(10.593f, 15.0f, 9.25f, 13.657f, 9.25f, 12.0f)
                    curveTo(9.25f, 10.343f, 10.593f, 9.0f, 12.25f, 9.0f)
                    curveTo(13.907f, 9.0f, 15.25f, 10.343f, 15.25f, 12.0f)
                    curveTo(15.25f, 13.657f, 13.907f, 15.0f, 12.25f, 15.0f)
                    close()
                    moveTo(20.5f, 12.203f)
                    curveTo(20.504f, 12.068f, 20.504f, 11.932f, 20.5f, 11.797f)
                    lineTo(21.899f, 10.05f)
                    curveTo(22.048f, 9.864f, 22.099f, 9.618f, 22.038f, 9.388f)
                    curveTo(21.808f, 8.526f, 21.465f, 7.699f, 21.017f, 6.927f)
                    curveTo(20.899f, 6.722f, 20.69f, 6.586f, 20.455f, 6.559f)
                    lineTo(18.231f, 6.311f)
                    curveTo(18.139f, 6.214f, 18.045f, 6.12f, 17.95f, 6.03f)
                    lineTo(17.688f, 3.801f)
                    curveTo(17.66f, 3.565f, 17.523f, 3.357f, 17.318f, 3.238f)
                    curveTo(16.546f, 2.791f, 15.719f, 2.449f, 14.857f, 2.219f)
                    curveTo(14.627f, 2.158f, 14.381f, 2.21f, 14.195f, 2.359f)
                    lineTo(12.453f, 3.75f)
                    curveTo(12.318f, 3.75f, 12.182f, 3.75f, 12.047f, 3.75f)
                    lineTo(10.3f, 2.354f)
                    curveTo(10.114f, 2.205f, 9.868f, 2.154f, 9.638f, 2.215f)
                    curveTo(8.776f, 2.445f, 7.949f, 2.788f, 7.177f, 3.235f)
                    curveTo(6.972f, 3.354f, 6.836f, 3.563f, 6.809f, 3.798f)
                    lineTo(6.561f, 6.025f)
                    curveTo(6.464f, 6.118f, 6.37f, 6.212f, 6.28f, 6.307f)
                    lineTo(4.051f, 6.563f)
                    curveTo(3.815f, 6.59f, 3.607f, 6.727f, 3.488f, 6.932f)
                    curveTo(3.041f, 7.704f, 2.699f, 8.531f, 2.469f, 9.393f)
                    curveTo(2.408f, 9.623f, 2.46f, 9.869f, 2.609f, 10.055f)
                    lineTo(4.0f, 11.797f)
                    curveTo(4.0f, 11.932f, 4.0f, 12.068f, 4.0f, 12.203f)
                    lineTo(2.604f, 13.95f)
                    curveTo(2.455f, 14.136f, 2.404f, 14.382f, 2.465f, 14.612f)
                    curveTo(2.695f, 15.474f, 3.038f, 16.301f, 3.485f, 17.073f)
                    curveTo(3.604f, 17.278f, 3.813f, 17.414f, 4.048f, 17.441f)
                    lineTo(6.272f, 17.689f)
                    curveTo(6.365f, 17.786f, 6.458f, 17.88f, 6.553f, 17.97f)
                    lineTo(6.813f, 20.199f)
                    curveTo(6.84f, 20.435f, 6.977f, 20.643f, 7.182f, 20.762f)
                    curveTo(7.954f, 21.209f, 8.781f, 21.551f, 9.643f, 21.781f)
                    curveTo(9.873f, 21.842f, 10.119f, 21.79f, 10.305f, 21.641f)
                    lineTo(12.047f, 20.25f)
                    curveTo(12.182f, 20.254f, 12.318f, 20.254f, 12.453f, 20.25f)
                    lineTo(14.2f, 21.649f)
                    curveTo(14.386f, 21.798f, 14.632f, 21.849f, 14.862f, 21.788f)
                    curveTo(15.724f, 21.558f, 16.551f, 21.215f, 17.323f, 20.767f)
                    curveTo(17.528f, 20.649f, 17.664f, 20.44f, 17.691f, 20.205f)
                    lineTo(17.939f, 17.981f)
                    curveTo(18.036f, 17.889f, 18.13f, 17.795f, 18.22f, 17.7f)
                    lineTo(20.449f, 17.438f)
                    curveTo(20.685f, 17.41f, 20.893f, 17.273f, 21.012f, 17.068f)
                    curveTo(21.459f, 16.296f, 21.801f, 15.469f, 22.031f, 14.607f)
                    curveTo(22.092f, 14.377f, 22.04f, 14.131f, 21.891f, 13.945f)
                    lineTo(20.5f, 12.203f)
                    close()
                    moveTo(18.991f, 11.593f)
                    curveTo(19.007f, 11.864f, 19.007f, 12.136f, 18.991f, 12.407f)
                    curveTo(18.979f, 12.592f, 19.038f, 12.776f, 19.154f, 12.921f)
                    lineTo(20.484f, 14.583f)
                    curveTo(20.331f, 15.068f, 20.136f, 15.538f, 19.9f, 15.989f)
                    lineTo(17.781f, 16.229f)
                    curveTo(17.597f, 16.25f, 17.426f, 16.338f, 17.303f, 16.477f)
                    curveTo(17.123f, 16.68f, 16.931f, 16.872f, 16.728f, 17.052f)
                    curveTo(16.589f, 17.175f, 16.5f, 17.346f, 16.48f, 17.53f)
                    lineTo(16.245f, 19.647f)
                    curveTo(15.794f, 19.883f, 15.324f, 20.079f, 14.838f, 20.231f)
                    lineTo(13.175f, 18.901f)
                    curveTo(13.042f, 18.795f, 12.877f, 18.737f, 12.707f, 18.737f)
                    horizontalLineTo(12.662f)
                    curveTo(12.391f, 18.753f, 12.119f, 18.753f, 11.848f, 18.737f)
                    curveTo(11.662f, 18.726f, 11.479f, 18.784f, 11.334f, 18.9f)
                    lineTo(9.667f, 20.231f)
                    curveTo(9.182f, 20.079f, 8.711f, 19.883f, 8.261f, 19.647f)
                    lineTo(8.021f, 17.531f)
                    curveTo(8.0f, 17.347f, 7.912f, 17.176f, 7.773f, 17.053f)
                    curveTo(7.57f, 16.873f, 7.378f, 16.681f, 7.198f, 16.478f)
                    curveTo(7.075f, 16.339f, 6.904f, 16.25f, 6.72f, 16.23f)
                    lineTo(4.603f, 15.994f)
                    curveTo(4.367f, 15.543f, 4.171f, 15.073f, 4.019f, 14.587f)
                    lineTo(5.349f, 12.924f)
                    curveTo(5.465f, 12.779f, 5.523f, 12.596f, 5.512f, 12.411f)
                    curveTo(5.496f, 12.14f, 5.496f, 11.868f, 5.512f, 11.597f)
                    curveTo(5.523f, 11.411f, 5.465f, 11.228f, 5.349f, 11.083f)
                    lineTo(4.019f, 9.417f)
                    curveTo(4.171f, 8.932f, 4.367f, 8.461f, 4.603f, 8.011f)
                    lineTo(6.719f, 7.771f)
                    curveTo(6.903f, 7.75f, 7.074f, 7.662f, 7.197f, 7.523f)
                    curveTo(7.377f, 7.32f, 7.57f, 7.128f, 7.773f, 6.948f)
                    curveTo(7.912f, 6.824f, 8.0f, 6.654f, 8.021f, 6.469f)
                    lineTo(8.256f, 4.353f)
                    curveTo(8.707f, 4.117f, 9.177f, 3.921f, 9.663f, 3.769f)
                    lineTo(11.326f, 5.099f)
                    curveTo(11.471f, 5.215f, 11.654f, 5.273f, 11.839f, 5.262f)
                    curveTo(12.11f, 5.246f, 12.382f, 5.246f, 12.653f, 5.262f)
                    curveTo(12.839f, 5.273f, 13.022f, 5.215f, 13.167f, 5.099f)
                    lineTo(14.833f, 3.769f)
                    curveTo(15.318f, 3.921f, 15.788f, 4.117f, 16.239f, 4.353f)
                    lineTo(16.479f, 6.469f)
                    curveTo(16.5f, 6.653f, 16.588f, 6.824f, 16.727f, 6.947f)
                    curveTo(16.93f, 7.127f, 17.122f, 7.32f, 17.302f, 7.523f)
                    curveTo(17.425f, 7.661f, 17.596f, 7.75f, 17.78f, 7.77f)
                    lineTo(19.897f, 8.005f)
                    curveTo(20.133f, 8.456f, 20.329f, 8.926f, 20.481f, 9.412f)
                    lineTo(19.151f, 11.075f)
                    curveTo(19.034f, 11.221f, 18.976f, 11.406f, 18.988f, 11.593f)
                    horizontalLineTo(18.991f)
                    close()
                }
            }
        }
        .build()
        return _settings!!
    }

private var _settings: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = SainggikIcon.Settings, contentDescription = "")
    }
}
