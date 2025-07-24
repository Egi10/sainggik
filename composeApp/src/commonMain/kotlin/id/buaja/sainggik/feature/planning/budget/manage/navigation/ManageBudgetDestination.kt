package id.buaja.sainggik.feature.planning.budget.manage.navigation

typealias OnManageBudgetDestination = (ManageBudgetDestination) -> Unit

sealed interface ManageBudgetDestination {
    data object NavigationBack : ManageBudgetDestination
}