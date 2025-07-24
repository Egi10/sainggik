package id.buaja.sainggik.feature.planning.budget.list.state

sealed interface ListBudgetListSideEffect {
    data class NavigateToEditBudget(val id: Long) : ListBudgetListSideEffect
}