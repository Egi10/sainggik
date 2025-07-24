package id.buaja.sainggik.domain.model.transactions

data class TransactionsRecentDaily(
    val averageSpending: Double,
    val daily: List<TransactionDailySpending>
)
