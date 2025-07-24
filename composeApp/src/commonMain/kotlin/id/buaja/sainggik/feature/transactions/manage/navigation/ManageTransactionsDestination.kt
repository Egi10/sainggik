package id.buaja.sainggik.feature.transactions.manage.navigation

typealias OnManageTransactionsDestination = (ManageTransactionsDestination) -> Unit

sealed interface ManageTransactionsDestination {
    data object NavigationBack : ManageTransactionsDestination
}