package id.buaja.sainggik.feature.planning.main.state

import id.buaja.sainggik.domain.model.budgets.Budget

data class BudgetsState(
    val isLoading: Boolean = false,
    val data: List<Budget> = emptyList(),
    val errorMessage: String? = null
)