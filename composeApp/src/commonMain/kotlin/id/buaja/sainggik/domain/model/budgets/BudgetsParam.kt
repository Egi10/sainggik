package id.buaja.sainggik.domain.model.budgets

data class BudgetsParam(
    val categoryId: Long,
    val startDate: String,
    val endDate: String,
    val amountLimit: Double
)
