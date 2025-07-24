package id.buaja.sainggik.feature.transactions.main.state

sealed interface TransactionsSideEffect {
    data object NavigateToAddTransaction : TransactionsSideEffect
    data class NavigateToEditTransaction(val id: Long) : TransactionsSideEffect
}