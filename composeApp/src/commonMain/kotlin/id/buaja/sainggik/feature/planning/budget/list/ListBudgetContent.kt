package id.buaja.sainggik.feature.planning.budget.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Empty
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.initials
import id.buaja.sainggik.core.ui.MessageFeedback
import id.buaja.sainggik.core.ui.SwipeableListItem
import id.buaja.sainggik.core.utils.AppLocale
import id.buaja.sainggik.core.utils.formatCurrency
import id.buaja.sainggik.domain.model.budgets.BudgetCategory
import id.buaja.sainggik.domain.model.budgets.BudgetGroup
import id.buaja.sainggik.feature.planning.budget.list.intent.BudgetListIntent
import id.buaja.sainggik.feature.planning.budget.list.intent.OnBudgetListIntent
import id.buaja.sainggik.feature.planning.budget.list.state.ListBudgetState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.empty_no_budgets_desc
import sainggik.composeapp.generated.resources.empty_no_budgets_title

@Composable
internal fun ListBudgetContent(
    state: ListBudgetState,
    onBudgetListIntent: OnBudgetListIntent,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> {
            // TODO: Show Loading Indicator
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
                title = stringResource(Res.string.empty_no_budgets_title),
                description = stringResource(Res.string.empty_no_budgets_desc)
            )
        }

        else -> {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(
                    vertical = 8.dp
                ),
                content = {
                    state.data.forEach { group ->
                        stickyHeader {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = SainggikTheme.colors.background
                                    )
                                    .padding(
                                        top = 16.dp,
                                        bottom = 8.dp,
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
                            SwipeableListItem(
                                item = it,
                                onDelete = { item ->
                                    onBudgetListIntent.invoke(
                                        BudgetListIntent.DeleteBudget(item.budgetId)
                                    )
                                },
                                onEdit = { item ->
                                    onBudgetListIntent.invoke(
                                        BudgetListIntent.EditBudget(item.budgetId)
                                    )
                                },
                                content = { item ->
                                    BudgetItem(
                                        item = it,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = SainggikTheme.colors.background
                                            )
                                            .padding(
                                                vertical = 8.dp,
                                                horizontal = 16.dp
                                            )
                                    )
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun BudgetItem(
    item: BudgetCategory,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
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

        Spacer(
            modifier = Modifier
                .width(16.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .align(
                    alignment = Alignment.CenterVertically
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp
            )
        ) {
            Text(
                text = item.name,
                style = SainggikTheme.typography.body,
                fontWeight = FontWeight.Medium,
                color = SainggikTheme.colors.onBackground
            )

            Text(
                text = formatCurrency(
                    amount = item.budgetAmount.toLong(),
                    locale = AppLocale.INDONESIA
                ),
                style = SainggikTheme.typography.label,
                fontWeight = FontWeight.Normal,
                color = SainggikTheme.colors.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun CategoryPlanningContentPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            ListBudgetContent(
                state = ListBudgetState(
                    isLoading = false,
                    data = dummyBudgetGroups,
                    errorMessage = null
                ),
                onBudgetListIntent = {}
            )
        }
    }
}

private val dummyBudgetGroups = listOf(
    BudgetGroup(
        startDate = "1 Jul",
        endDate = "25 Jul, 2025",
        rawStartDate = "2025-07-01",
        categories = listOf(
            BudgetCategory(
                categoryId = 1L,
                budgetId = 1L,
                name = "Food",
                budgetAmount = 1_000_000.0
            ),
            BudgetCategory(
                categoryId = 2L,
                budgetId = 2L,
                name = "Transportation",
                budgetAmount = 500_000.0
            ),
            BudgetCategory(
                categoryId = 3L,
                budgetId = 3L,
                name = "Entertainment",
                budgetAmount = 750_000.0
            )
        )
    ),
    BudgetGroup(
        startDate = "28 Dec 2025",
        endDate = "5 Jan 2026",
        rawStartDate = "2025-12-28",
        categories = listOf(
            BudgetCategory(
                categoryId = 4L,
                budgetId = 4L,
                name = "Groceries",
                budgetAmount = 1_200_000.0
            ),
            BudgetCategory(
                categoryId = 5L,
                budgetId = 5L,
                name = "Utilities",
                budgetAmount = 800_000.0
            )
        )
    )
)
