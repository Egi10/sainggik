package id.buaja.sainggik.feature.transactions.main.intent

typealias OnTransactionsIntent = (TransactionsIntent) -> Unit

sealed interface TransactionsIntent {
    data class ChangeTab(val index: Int) : TransactionsIntent
    data object FabClicked : TransactionsIntent

    data class OnDeleteTransactionsClick(val id: Long) : TransactionsIntent
    data class OnEditTransactionsClick(val id: Long): TransactionsIntent
}