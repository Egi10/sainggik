package id.buaja.sainggik.feature.transactions.main.state

import id.buaja.sainggik.domain.model.transactions.TransactionGroup

data class TransactionsState(
    val selectedTab: Int = 0,
    val transactionsListState: TransactionsListState = TransactionsListState()
)

data class TransactionsListState(
    val isLoading: Boolean = false,
    val data: List<TransactionGroup> = emptyList(),
    val errorMessage: String? = null
)