package id.buaja.sainggik.core.designsystem.icon.collection

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import org.jetbrains.compose.resources.painterResource
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.img_warning

val SainggikIcon.Warning: Painter
    @Composable
    get() = painterResource(Res.drawable.img_warning)