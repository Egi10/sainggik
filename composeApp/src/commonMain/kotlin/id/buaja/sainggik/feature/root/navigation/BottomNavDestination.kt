package id.buaja.sainggik.feature.root.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Budget
import id.buaja.sainggik.core.designsystem.icon.collection.Home
import id.buaja.sainggik.core.designsystem.icon.collection.Transactions
import id.buaja.sainggik.feature.home.navigation.HomeRoute
import id.buaja.sainggik.feature.planning.main.navigation.PlanningRoute
import id.buaja.sainggik.feature.transactions.main.navigation.TransactionsRoute

enum class BottomNavDestination(
    val text: String,
    val icon: ImageVector,
    val route: Any
) {
    HOME(
        text = "Home",
        icon = SainggikIcon.Home,
        route = HomeRoute
    ),
    TRANSACTION(
        text = "Transactions",
        icon = SainggikIcon.Transactions,
        route = TransactionsRoute
    ),
    BUDGET(
        text = "Planning",
        icon = SainggikIcon.Budget,
        route = PlanningRoute
    )
}