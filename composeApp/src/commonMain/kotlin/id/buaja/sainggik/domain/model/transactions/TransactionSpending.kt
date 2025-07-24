package id.buaja.sainggik.domain.model.transactions

data class TransactionSpending(
    val categoryId: Long,
    val categoryName: String,
    val totalExpense: Double,
    val expensePercentage: Double,
    val totalAllExpense: Double
)
