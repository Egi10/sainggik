package id.buaja.sainggik.domain.model.budgets

data class BudgetGroup(
    val startDate: String,
    val endDate: String,
    val rawStartDate: String,
    val categories: List<BudgetCategory>
)

data class BudgetCategory(
    val budgetId: Long,
    val categoryId: Long,
    val name: String,
    val budgetAmount: Double,
)
