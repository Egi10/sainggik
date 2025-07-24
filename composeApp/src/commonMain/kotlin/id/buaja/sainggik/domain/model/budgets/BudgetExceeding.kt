package id.buaja.sainggik.domain.model.budgets

data class BudgetExceeding(
    val budgetId: Long,
    val categoryName: String,
    val startDate: String,
    val endDate: String,
    val amountLimit: Double,
    val totalExpense: Double,
    val expensePercentage: Double
)
