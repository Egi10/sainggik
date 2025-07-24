package id.buaja.sainggik.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.buaja.sainggik.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object HomeBaseRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(
    route = HomeRoute,
    navOptions = navOptions
)

fun NavGraphBuilder.homeSection(
    settingDestination: NavGraphBuilder.() -> Unit
) {
    navigation<HomeBaseRoute>(
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeRoute()
        }
        settingDestination()
    }
}