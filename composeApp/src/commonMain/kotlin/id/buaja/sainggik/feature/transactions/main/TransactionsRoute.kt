package id.buaja.sainggik.feature.transactions.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import id.buaja.sainggik.feature.transactions.main.state.TransactionsSideEffect
import id.buaja.sainggik.feature.transactions.main.navigation.OnTransactionsDestination
import id.buaja.sainggik.feature.transactions.main.navigation.TransactionsDestination
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun TransactionsRoute(
    onTransactionsDestination: OnTransactionsDestination,
    modifier: Modifier = Modifier,
    viewModel: TransactionsViewModel = koinViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            TransactionsSideEffect.NavigateToAddTransaction -> {
                onTransactionsDestination.invoke(TransactionsDestination.NavigateToAddTransaction)
            }

            is TransactionsSideEffect.NavigateToEditTransaction -> {
                onTransactionsDestination.invoke(TransactionsDestination.NavigateToEditTransaction(transactionId = it.id))
            }
        }
    }

    TransactionsScreen(
        modifier = modifier,
        state = state,
        onTransactionsIntent = viewModel::onIntent
    )
}