package id.buaja.sainggik.feature.planning.summary.state

import id.buaja.sainggik.domain.model.budgets.BudgetSummaryGroup

data class BudgetSummaryState(
    val isLoading: Boolean = false,
    val data: List<BudgetSummaryGroup> = emptyList(),
    val errorMessage: String? = null
)
