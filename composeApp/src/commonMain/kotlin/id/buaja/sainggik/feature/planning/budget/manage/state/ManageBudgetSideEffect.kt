package id.buaja.sainggik.feature.planning.budget.manage.state

sealed interface ManageBudgetSideEffect {
    data object NavigateBack : ManageBudgetSideEffect

    data object ShowCategoryBottomSheet : ManageBudgetSideEffect
    data object ShowStartDateBottomSheet : ManageBudgetSideEffect
    data object ShowEndDateBottomSheet : ManageBudgetSideEffect

    data class ShowToastMessage(val message: String) : ManageBudgetSideEffect
}