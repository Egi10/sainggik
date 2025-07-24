package id.buaja.sainggik.domain.model.transactions

data class Transaction(
    val id: Long,
    val amount: Double,
    val categoryId: Long,
    val note: String,
    val transactionDate: String
)
