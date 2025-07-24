package id.buaja.sainggik.domain.model.transactions

data class TransactionsUpdateParam(
    val id: Long,
    val amount: Double,
    val categoryId: Long,
    val note: String,
    val transactionDate: String
)
