package id.buaja.sainggik.domain.model.budgets

data class Budget(
    val id: Long,
    val categoryId: Long,
    val startDate: String,
    val endDate: String,
    val amountLimit: Double,
    val createdAt: String
)