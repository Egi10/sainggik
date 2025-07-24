package id.buaja.sainggik.feature.root.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import id.buaja.sainggik.feature.home.navigation.HomeBaseRoute
import id.buaja.sainggik.feature.home.navigation.homeSection
import id.buaja.sainggik.feature.planning.budget.manage.navigation.ManageBudgetDestination
import id.buaja.sainggik.feature.planning.budget.manage.navigation.navigateToManageBudget
import id.buaja.sainggik.feature.planning.main.navigation.PlanningDestination
import id.buaja.sainggik.feature.planning.main.navigation.planningSection
import id.buaja.sainggik.feature.saving.navigation.SavingDestination
import id.buaja.sainggik.feature.saving.navigation.navigateToSavingHistory
import id.buaja.sainggik.feature.saving.navigation.savingSection
import id.buaja.sainggik.feature.splash.navigation.SplashRoute
import id.buaja.sainggik.feature.splash.navigation.splashScreen
import id.buaja.sainggik.feature.transactions.manage.navigation.ManageTransactionsDestination
import id.buaja.sainggik.feature.transactions.manage.navigation.navigateToManageTransaction
import id.buaja.sainggik.feature.transactions.main.navigation.TransactionsDestination
import id.buaja.sainggik.feature.transactions.main.navigation.transactionsSection

@Composable
fun RootNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeBaseRoute,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        modifier = modifier
    ) {
        // TODO : Belum dipakai dulu, sepertinya cari lottie dan icon dulu
        splashScreen(
            onFinish = {
                navHostController.navigate(HomeBaseRoute) {
                    popUpTo(SplashRoute) {
                        inclusive = true
                    }
                }
            }
        )

        homeSection(
            settingDestination = {

            }
        )

        transactionsSection(
            onTransactionsDestination = {
                when (it) {
                    TransactionsDestination.NavigateToAddTransaction -> {
                        navHostController.navigateToManageTransaction()
                    }

                    is TransactionsDestination.NavigateToEditTransaction -> {
                        navHostController.navigateToManageTransaction(
                            transactionId = it.transactionId
                        )
                    }
                }
            },
            onManageTransactionsDestination = {
                when (it) {
                    ManageTransactionsDestination.NavigationBack -> {
                        navHostController.popBackStack()
                    }
                }
            }
        )

        savingSection(
            onSavingDestination = {
                when (it) {
                    is SavingDestination.NavigateToDetail -> {
                        navHostController.navigateToSavingHistory()
                    }
                }
            }
        )

        planningSection(
            onPlanningDestination = {
                when (it) {
                    is PlanningDestination.NavigationToManageBudget -> {
                        navHostController.navigateToManageBudget(
                            budgetId = it.budgetId
                        )
                    }
                }
            },
            onManageBudgetDestination = {
                when (it) {
                    ManageBudgetDestination.NavigationBack -> {
                        navHostController.popBackStack()
                    }
                }
            }
        )
    }
}