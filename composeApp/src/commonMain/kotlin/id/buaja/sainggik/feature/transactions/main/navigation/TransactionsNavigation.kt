package id.buaja.sainggik.feature.transactions.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.buaja.sainggik.feature.transactions.manage.navigation.OnManageTransactionsDestination
import id.buaja.sainggik.feature.transactions.manage.navigation.manageTransactionsScreen
import id.buaja.sainggik.feature.transactions.main.TransactionsRoute
import kotlinx.serialization.Serializable

@Serializable
object TransactionsBaseRoute

@Serializable
object TransactionsRoute

fun NavGraphBuilder.transactionsSection(
    onTransactionsDestination: OnTransactionsDestination,
    onManageTransactionsDestination: OnManageTransactionsDestination
) {
    navigation<TransactionsBaseRoute>(
        startDestination = TransactionsRoute
    ) {
        composable<TransactionsRoute> {
            TransactionsRoute(
                onTransactionsDestination = onTransactionsDestination
            )
        }

        manageTransactionsScreen(
            onManageTransactionsDestination = onManageTransactionsDestination
        )
    }
}