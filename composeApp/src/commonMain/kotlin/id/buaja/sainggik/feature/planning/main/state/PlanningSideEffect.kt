package id.buaja.sainggik.feature.planning.main.state

sealed interface PlanningSideEffect {
    data object ShowAddCategoryBottomSheet : PlanningSideEffect
    data object LoadCategoryData : PlanningSideEffect
    data object LoadSummary : PlanningSideEffect
    data object LoadBudget : PlanningSideEffect
    data class NavigateManageBudget(val budgetId: Long? = null) : PlanningSideEffect
}