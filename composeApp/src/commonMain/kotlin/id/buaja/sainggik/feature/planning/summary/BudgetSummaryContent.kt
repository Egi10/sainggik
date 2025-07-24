package id.buaja.sainggik.feature.planning.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.components.SainggikProgressIndicator
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Empty
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.initials
import id.buaja.sainggik.core.ui.MessageFeedback
import id.buaja.sainggik.core.utils.AppLocale
import id.buaja.sainggik.core.utils.formatCurrency
import id.buaja.sainggik.domain.model.budgets.BudgetSummaryCategory
import id.buaja.sainggik.domain.model.budgets.BudgetSummaryGroup
import id.buaja.sainggik.feature.planning.summary.state.BudgetSummaryState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun BudgetSummaryContent(
    state: BudgetSummaryState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        when {
            state.isLoading -> {
                // TODO: Show Loading Shimmer
            }

            !state.errorMessage.isNullOrEmpty() -> {
                // TODO: Show Error UI with state.errorMessage
            }

            state.data.isEmpty() -> {
                MessageFeedback(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 24.dp,
                            horizontal = 16.dp
                        ),
                    image = SainggikIcon.Empty,
                    title = "No Data to Summarize",
                    description = "Once you start adding transactions, we’ll show you your financial overview here."
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        vertical = 12.dp
                    )
                ) {
                    state.data.forEach { group ->
                        stickyHeader {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = SainggikTheme.colors.background
                                    )
                                    .padding(
                                        top = 20.dp,
                                        bottom = 12.dp,
                                        start = 16.dp,
                                        end = 16.dp
                                    )
                            ) {
                                Text(
                                    text = "${group.startDate} - ${group.endDate}",
                                    style = SainggikTheme.typography.title,
                                    color = SainggikTheme.colors.onBackground
                                )
                            }
                        }

                        items(
                            items = group.categories,
                            key = {
                                it.budgetId
                            }
                        ) {
                            BudgetSummaryItem(
                                item = it,
                                modifier = Modifier
                                    .padding(
                                        vertical = 12.dp,
                                        horizontal = 16.dp
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BudgetSummaryItem(
    item: BudgetSummaryCategory,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp
        )
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = SainggikTheme.colors.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = item.name.initials(),
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.onBackground
            )
        }

        Column(
            modifier = Modifier
                .weight(1.2f),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp
            )
        ) {
            Text(
                text = item.name,
                style = SainggikTheme.typography.body,
                color = SainggikTheme.colors.onBackground,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Budget: ${
                    formatCurrency(
                        amount = item.budgetAmount.toLong(),
                        locale = AppLocale.INDONESIA
                    )
                }",
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Remaining: ${
                    formatCurrency(
                        amount = item.remainingAmount.toLong(),
                        locale = AppLocale.INDONESIA
                    )
                }",
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Spent: ${
                    formatCurrency(
                        amount = item.spentAmount.toLong(),
                        locale = AppLocale.INDONESIA
                    )
                }",
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            )
        }

        SainggikProgressIndicator(
            modifier = Modifier
                .weight(1f),
            progress = item.progress
        )
    }
}

@Preview
@Composable
private fun BudgetListContentPreview() {
    SainggikTheme {
        BudgetSummaryContent(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                ),
            state = BudgetSummaryState(
                data = budgetSummaryGroups
            )
        )
    }
}

private val budgetSummaryGroups = listOf(
    BudgetSummaryGroup(
        startDate = "Jul 1",
        endDate = "Jul 25, 2025",
        rawStartDate = "",
        categories = listOf(
            BudgetSummaryCategory(
                categoryId = 1,
                budgetId = 1,
                name = "Food",
                budgetAmount = 1000.0,
                remainingAmount = 700.0,
                spentAmount = 300.0,
                progress = 70
            ),
            BudgetSummaryCategory(
                categoryId = 2,
                budgetId = 2,
                name = "Transportation",
                budgetAmount = 2000.0,
                remainingAmount = 600.0,
                spentAmount = 1400.0,
                progress = 30
            ),
            BudgetSummaryCategory(
                categoryId = 3,
                budgetId = 3,
                name = "Entertainment",
                budgetAmount = 1500.0,
                remainingAmount = 100.0,
                spentAmount = 1400.0,
                progress = 80
            )
        )
    ),
    BudgetSummaryGroup(
        startDate = "Dec 28, 2025",
        endDate = "Jan 5, 2026",
        rawStartDate = "",
        categories = listOf(
            BudgetSummaryCategory(
                categoryId = 10,
                budgetId = 10,
                name = "Food",
                budgetAmount = 1500.0,
                remainingAmount = 400.0,
                spentAmount = 1100.0,
                progress = 73
            ),
            BudgetSummaryCategory(
                categoryId = 11,
                budgetId = 11,
                name = "Transportation",
                budgetAmount = 3000.0,
                remainingAmount = 500.0,
                spentAmount = 2500.0,
                progress = 83
            ),
            BudgetSummaryCategory(
                categoryId = 12,
                budgetId = 11,
                name = "Entertainment",
                budgetAmount = 2000.0,
                remainingAmount = 800.0,
                spentAmount = 1200.0,
                progress = 60
            )
        )
    )
)
