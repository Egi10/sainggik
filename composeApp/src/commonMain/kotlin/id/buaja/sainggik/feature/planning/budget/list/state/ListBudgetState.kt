package id.buaja.sainggik.feature.planning.budget.list.state

import id.buaja.sainggik.domain.model.budgets.BudgetGroup

data class ListBudgetState(
    val isLoading: Boolean = false,
    val data: List<BudgetGroup> = emptyList(),
    val errorMessage: String? = null
)