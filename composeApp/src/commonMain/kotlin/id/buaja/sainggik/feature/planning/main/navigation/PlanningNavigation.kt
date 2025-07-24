package id.buaja.sainggik.feature.planning.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import id.buaja.sainggik.feature.planning.budget.manage.navigation.OnManageBudgetDestination
import id.buaja.sainggik.feature.planning.budget.manage.navigation.manageBudgetScreen
import id.buaja.sainggik.feature.planning.main.PlanningRoute
import kotlinx.serialization.Serializable

@Serializable
data object PlanningRoute

fun NavGraphBuilder.planningSection(
    onPlanningDestination: OnPlanningDestination,
    onManageBudgetDestination: OnManageBudgetDestination
) {
    composable<PlanningRoute> {
        PlanningRoute(
            onPlanningDestination = onPlanningDestination
        )
    }

    manageBudgetScreen(
        onManageBudgetDestination = onManageBudgetDestination
    )
}