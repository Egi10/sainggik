package id.buaja.sainggik.domain.model.budgets

data class BudgetSummaryGroup(
    val startDate: String,
    val endDate: String,
    val rawStartDate: String,
    val categories: List<BudgetSummaryCategory>
)

data class BudgetSummaryCategory(
    val budgetId: Long,
    val categoryId: Long,
    val name: String,
    val budgetAmount: Double,
    val remainingAmount: Double,
    val spentAmount: Double,
    val progress: Int
)
