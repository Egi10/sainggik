package id.buaja.sainggik.feature.planning.budget.manage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.toRoute
import id.buaja.sainggik.feature.planning.budget.manage.ManageBudgetRoute
import id.buaja.sainggik.feature.root.extensions.animatedComposable
import kotlinx.serialization.Serializable

@Serializable
data class ManageBudgetRoute(
    val budgetId: Long? = null
)

fun NavGraphBuilder.manageBudgetScreen(
    onManageBudgetDestination: OnManageBudgetDestination
) {
    animatedComposable<ManageBudgetRoute> { backStackEntry ->
        val manageBudget = backStackEntry.toRoute<ManageBudgetRoute>()
        ManageBudgetRoute(
            onManageBudgetDestination = onManageBudgetDestination,
            budgetId = manageBudget.budgetId
        )
    }
}

fun NavController.navigateToManageBudget(
    budgetId: Long? = null,
    navOptions: NavOptions? = null
) = navigate(
    route = ManageBudgetRoute(
        budgetId = budgetId
    ),
    navOptions = navOptions
)