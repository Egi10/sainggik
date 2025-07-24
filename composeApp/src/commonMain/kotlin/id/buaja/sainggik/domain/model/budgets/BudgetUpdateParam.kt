package id.buaja.sainggik.domain.model.budgets

data class BudgetUpdateParam(
    val id: Long,
    val startDate: String,
    val endDate: String,
    val amountLimit: Double,
)
