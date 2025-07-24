package id.buaja.sainggik.feature.transactions.manage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.toRoute
import id.buaja.sainggik.feature.root.extensions.animatedComposable
import id.buaja.sainggik.feature.transactions.manage.ManageTransactionRoute
import kotlinx.serialization.Serializable


@Serializable
data class ManageTransactionRoute(
    val transactionId: Long? = null
)

fun NavGraphBuilder.manageTransactionsScreen(
    onManageTransactionsDestination: OnManageTransactionsDestination
) {
    animatedComposable<ManageTransactionRoute> { backStackEntry ->
        val manageTransaction = backStackEntry.toRoute<ManageTransactionRoute>()
        ManageTransactionRoute(
            onManageTransactionsDestination = onManageTransactionsDestination,
            transactionId = manageTransaction.transactionId
        )
    }
}

fun NavController.navigateToManageTransaction(
    transactionId: Long? = null,
    navOptions: NavOptions? = null
) = navigate(
    route = ManageTransactionRoute(
        transactionId = transactionId
    ),
    navOptions = navOptions
)