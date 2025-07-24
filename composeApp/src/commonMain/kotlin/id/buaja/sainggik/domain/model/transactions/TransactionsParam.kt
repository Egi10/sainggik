package id.buaja.sainggik.domain.model.transactions

data class TransactionsParam(
    val amount: Double,
    val categoryId: Long,
    val note: String,
    val transactionDate: String
)
