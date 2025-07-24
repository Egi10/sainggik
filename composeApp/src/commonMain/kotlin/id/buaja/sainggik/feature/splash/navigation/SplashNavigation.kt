package id.buaja.sainggik.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import id.buaja.sainggik.feature.root.extensions.animatedComposable
import id.buaja.sainggik.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

fun NavGraphBuilder.splashScreen(
    onFinish: () -> Unit
) {
    animatedComposable<SplashRoute> {
        SplashRoute(
            onFinish = onFinish
        )
    }
}