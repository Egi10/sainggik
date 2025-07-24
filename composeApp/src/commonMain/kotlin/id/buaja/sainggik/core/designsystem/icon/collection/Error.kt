package id.buaja.sainggik.core.designsystem.icon.collection

import androidx.compose.runtime.Composable
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import org.jetbrains.compose.resources.painterResource
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.img_error

val SainggikIcon.Error
    @Composable
    get() = painterResource(Res.drawable.img_error)