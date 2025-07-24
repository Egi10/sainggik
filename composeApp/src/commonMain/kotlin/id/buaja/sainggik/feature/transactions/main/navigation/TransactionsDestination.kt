package id.buaja.sainggik.feature.transactions.main.navigation

typealias OnTransactionsDestination = (TransactionsDestination) -> Unit

sealed interface TransactionsDestination {
    data object NavigateToAddTransaction : TransactionsDestination
    data class NavigateToEditTransaction(val transactionId: Long) : TransactionsDestination
}