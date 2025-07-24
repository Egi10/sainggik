package id.buaja.sainggik.feature.planning.main.navigation

typealias OnPlanningDestination = (PlanningDestination) -> Unit

sealed interface PlanningDestination {
    data class NavigationToManageBudget(val budgetId: Long? = null) : PlanningDestination
}