package id.buaja.sainggik.feature.saving.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.buaja.sainggik.feature.root.extensions.animatedComposable
import id.buaja.sainggik.feature.saving.history.SavingHistoryRoute
import id.buaja.sainggik.feature.saving.main.SavingRoute
import kotlinx.serialization.Serializable

@Serializable
data object SavingBaseRoute

@Serializable
data object SavingRoute

@Serializable
data object SavingHistoryRoute

fun NavGraphBuilder.savingSection(
    onSavingDestination: OnSavingDestination,
) {
    navigation<SavingBaseRoute>(
        startDestination = SavingRoute
    ) {
        composable<SavingRoute> {
            SavingRoute(
                onSavingDestination = onSavingDestination
            )
        }

        animatedComposable<SavingHistoryRoute> {
            SavingHistoryRoute()
        }
    }
}

fun NavController.navigateToSavingHistory(navOptions: NavOptions? = null) = navigate(
    route = SavingHistoryRoute,
    navOptions = navOptions
)