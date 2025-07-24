package id.buaja.sainggik.domain.model.transactions

data class TransactionGroup(
    val dateLabel: String,
    val items: List<TransactionItem>
)

data class TransactionItem(
    val id: Long,
    val title: String,
    val category: String,
    val amount: String
)