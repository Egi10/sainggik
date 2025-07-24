package id.buaja.sainggik.feature.transactions.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.components.SainggikFloatingActionButton
import id.buaja.sainggik.core.designsystem.components.SainggikScrollableTab
import id.buaja.sainggik.core.designsystem.components.SainggikTopAppBar
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.zeroInsets
import id.buaja.sainggik.domain.model.transactions.TransactionGroup
import id.buaja.sainggik.domain.model.transactions.TransactionItem
import id.buaja.sainggik.feature.transactions.main.component.TransactionsSection
import id.buaja.sainggik.feature.transactions.main.intent.OnTransactionsIntent
import id.buaja.sainggik.feature.transactions.main.intent.TransactionsIntent
import id.buaja.sainggik.feature.transactions.main.state.TransactionsListState
import id.buaja.sainggik.feature.transactions.main.state.TransactionsState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.label_expenses
import sainggik.composeapp.generated.resources.label_income
import sainggik.composeapp.generated.resources.title_transactions

@Composable
internal fun TransactionsScreen(
    state: TransactionsState,
    onTransactionsIntent: OnTransactionsIntent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = SainggikTheme.colors.background,
        contentWindowInsets = WindowInsets.zeroInsets,
        topBar = {
            SainggikTopAppBar(
                title = stringResource(Res.string.title_transactions)
            )
        },
        floatingActionButton = {
            SainggikFloatingActionButton(
                onClick = {
                    onTransactionsIntent.invoke(TransactionsIntent.FabClicked)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            TransactionsContent(
                modifier = Modifier
                    .padding(
                        paddingValues = paddingValues
                    ),
                state = state,
                onTransactionsIntent = onTransactionsIntent
            )
        }
    )
}

@Composable
private fun TransactionsContent(
    state: TransactionsState,
    onTransactionsIntent: OnTransactionsIntent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp
        )
    ) {
        SainggikScrollableTab(
            tabs = listOf(
                stringResource(Res.string.label_expenses),
                stringResource(Res.string.label_income)
            ),
            selectedTabIndex = state.selectedTab,
            onTabSelected = {
                onTransactionsIntent.invoke(TransactionsIntent.ChangeTab(it))
            }
        )

        TransactionsSection(
            onTransactionsIntent = onTransactionsIntent,
            state = state.transactionsListState
        )
    }
}

@Preview
@Composable
private fun TransactionsScreenPreview() {
    SainggikTheme {
        TransactionsScreen(
            onTransactionsIntent = {},
            state = TransactionsState(
                selectedTab = 0,
                transactionsListState = TransactionsListState(
                    data = dummyGroupedTransactions
                )
            )
        )
    }
}

private val dummyGroupedTransactions = listOf(
    TransactionGroup(
        dateLabel = "Today",
        items = listOf(
            TransactionItem(1, "Supermarket Supermarket Supermarket Supermarket", "Groceries", "-$45.20"),
            TransactionItem(2, "Apartment Rent", "Rent", "-$1,200.00"),
            TransactionItem(3, "Coffee Shop", "Food & Drink", "-$5.75")
        )
    ),
    TransactionGroup(
        dateLabel = "Yesterday",
        items = listOf(
            TransactionItem(4, "Electricity Bill", "Utilities", "-$85.50"),
            TransactionItem(5, "Restaurant Dinner", "Dining Out", "-$62.75"),
            TransactionItem(6, "Spotify Subscription", "Entertainment", "-$9.99")
        )
    ),
    TransactionGroup(
        dateLabel = "June 26, 2025",
        items = listOf(
            TransactionItem(7, "Internet Bill", "Utilities", "-$55.00"),
            TransactionItem(8, "Gas Refill", "Transport", "-$30.00")
        )
    ),
    TransactionGroup(
        dateLabel = "June 25, 2025",
        items = listOf(
            TransactionItem(9, "Freelance Payment", "Income", "+$500.00"),
            TransactionItem(10, "Grocery Store", "Groceries", "-$65.40")
        )
    ),
    TransactionGroup(
        dateLabel = "June 24, 2025",
        items = listOf(
            TransactionItem(11, "Cinema", "Entertainment", "-$12.00"),
            TransactionItem(12, "Bus Pass", "Transport", "-$25.00"),
            TransactionItem(13, "Book Purchase", "Education", "-$18.50")
        )
    )
)
