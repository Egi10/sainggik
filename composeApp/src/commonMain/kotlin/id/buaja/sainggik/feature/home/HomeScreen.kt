package id.buaja.sainggik.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.components.SainggikTopAppBar
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.zeroInsets
import id.buaja.sainggik.feature.home.component.BudgetAlerts
import id.buaja.sainggik.feature.home.component.DailySpendingTrends
import id.buaja.sainggik.feature.home.component.SpendingOverview
import id.buaja.sainggik.feature.home.component.TransactionFilter
import id.buaja.sainggik.feature.home.component.TransactionOverview
import id.buaja.sainggik.feature.home.intent.OnHomeIntent
import id.buaja.sainggik.feature.home.state.BudgetsExceedingState
import id.buaja.sainggik.feature.home.state.HomeFilterEnum
import id.buaja.sainggik.feature.home.state.HomeFilterState
import id.buaja.sainggik.feature.home.state.TransactionDailySpendingState
import id.buaja.sainggik.feature.home.state.TransactionSpendingState
import id.buaja.sainggik.feature.home.state.TransactionSummaryState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeScreen(
    filterSelectable: HomeFilterState?,
    filters: List<HomeFilterState>,
    transactionSummaryState: TransactionSummaryState,
    transactionSpendingState: TransactionSpendingState,
    transactionDailySpendingState: TransactionDailySpendingState,
    budgetsExceedingState: BudgetsExceedingState,
    onHomeIntent: OnHomeIntent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = SainggikTheme.colors.background,
        contentWindowInsets = WindowInsets.zeroInsets,
        topBar = {
            SainggikTopAppBar(
                title = "Home"
            )
        },
        content = { paddingValues ->
            HomeContent(
                filterSelectable = filterSelectable,
                filters = filters,
                transactionSummaryState = transactionSummaryState,
                transactionSpendingState = transactionSpendingState,
                transactionDailySpendingState = transactionDailySpendingState,
                budgetsExceedingState = budgetsExceedingState,
                onHomeIntent = onHomeIntent,
                modifier = Modifier
                    .padding(
                        paddingValues = paddingValues
                    )
            )
        }
    )
}

@Composable
private fun HomeContent(
    filterSelectable: HomeFilterState?,
    filters: List<HomeFilterState>,
    transactionSummaryState: TransactionSummaryState,
    transactionSpendingState: TransactionSpendingState,
    transactionDailySpendingState: TransactionDailySpendingState,
    budgetsExceedingState: BudgetsExceedingState,
    onHomeIntent: OnHomeIntent,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            bottom = 20.dp
        )
    ) {
        item(
            key = "transaction_filter"
        ) {
            TransactionFilter(
                filterSelectable = filterSelectable,
                filters = filters,
                onHomeIntent = onHomeIntent
            )
        }
        item(
            key = "transaction_overview"
        ) {
            TransactionOverview(
                filterSelectable = filterSelectable,
                transactionSummaryState = transactionSummaryState,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item(
            key = "spending_overview"
        ) {
            SpendingOverview(
                transactionSpendingState = transactionSpendingState,
                homeFilterState = filterSelectable,
                onHomeIntent = onHomeIntent,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item(
            key = "daily_spending_trends"
        ) {
            DailySpendingTrends(
                transactionDailySpendingState = transactionDailySpendingState,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item(
            key = "budget_alerts"
        ) {
            BudgetAlerts(
                budgetsExceedingState = budgetsExceedingState,
                onHomeIntent = onHomeIntent,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
                .statusBarsPadding()
        ) {
            HomeScreen(
                filterSelectable = HomeFilterState(
                    key = HomeFilterEnum.THIS_WEEK,
                    value = "This Week"
                ),
                filters = listOf(
                    HomeFilterState(
                        key = HomeFilterEnum.THIS_WEEK,
                        value = "This Week"
                    ),
                    HomeFilterState(
                        key = HomeFilterEnum.THIS_MONTH,
                        value = "This Month"
                    ),
                    HomeFilterState(
                        key = HomeFilterEnum.CUSTOM,
                        value = "Custom"

                    )
                ),
                transactionSummaryState = TransactionSummaryState(),
                transactionSpendingState = TransactionSpendingState.Empty,
                transactionDailySpendingState = TransactionDailySpendingState.Empty,
                budgetsExceedingState = BudgetsExceedingState.Empty,
                onHomeIntent = {}
            )
        }
    }
}