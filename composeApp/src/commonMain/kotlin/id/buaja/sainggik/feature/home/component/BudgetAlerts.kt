package id.buaja.sainggik.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.designsystem.components.SainggikChips
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Warning
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.domain.model.budgets.BudgetExceeding
import id.buaja.sainggik.feature.home.intent.HomeIntent
import id.buaja.sainggik.feature.home.intent.OnHomeIntent
import id.buaja.sainggik.feature.home.state.BudgetsExceedingState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.*

@Composable
internal fun BudgetAlerts(
    budgetsExceedingState: BudgetsExceedingState,
    onHomeIntent: OnHomeIntent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.title_budget_alert),
            style = SainggikTheme.typography.title,
            color = SainggikTheme.colors.onBackground,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
        )

        when (budgetsExceedingState) {
            BudgetsExceedingState.Loading -> {

            }

            BudgetsExceedingState.Empty -> {
                TransactionEmpty(
                    title = stringResource(Res.string.empty_no_budget_alerts_title),
                    description = stringResource(Res.string.empty_no_budget_alerts_desc),
                    modifier = Modifier
                        .padding(
                            all = 16.dp
                        )
                )
            }

            is BudgetsExceedingState.Success -> {
                Alert(
                    data = budgetsExceedingState.data,
                    onHomeIntent = onHomeIntent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            all = 16.dp
                        )
                )
            }

            is BudgetsExceedingState.Error -> {

            }
        }
    }
}

@Composable
private fun Alert(
    data: List<BudgetExceeding>,
    onHomeIntent: OnHomeIntent,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp
            )
        ) {
            Text(
                text = stringResource(Res.string.title_budget_alert),
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.secondary,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = stringResource(Res.string.desc_exceeded_budget_categories, data.size),
                style = SainggikTheme.typography.body,
                color = SainggikTheme.colors.onBackground,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(
                    resource = Res.string.label_budget_alert_period,
                    formatArgs = arrayOf(
                        DateTimeHelper.formatDate(
                            date = data.first().startDate,
                            inputFormat = DateTimeHelper.Formats.DATE,
                            outputFormat = DateTimeHelper.Formats.DATE_SHORT
                        ),
                        DateTimeHelper.formatDate(
                            date = data.first().endDate,
                            inputFormat = DateTimeHelper.Formats.DATE,
                            outputFormat = DateTimeHelper.Formats.DATE_SHORT
                        )
                    )
                ),
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.secondary,
                fontWeight = FontWeight.Normal
            )
            
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            // TODO Rilis Dulu
//            SainggikChips(
//                selected = false,
//                label = stringResource(Res.string.button_view_details),
//                onClick = {
//                    onHomeIntent.invoke(HomeIntent.OnDetailBudgetAlertClick)
//                }
//            )
        }

        Image(
            painter = SainggikIcon.Warning,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun BudgetAlertPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            BudgetAlerts(
                budgetsExceedingState = BudgetsExceedingState.Success(
                    data = dummyBudgetsExceeding
                ),
                onHomeIntent = { }
            )
        }
    }
}

private val dummyBudgetsExceeding = listOf(
    BudgetExceeding(
        budgetId = 1L,
        categoryName = "Makanan & Minuman",
        startDate = "2025-07-01",
        endDate = "2025-07-31",
        amountLimit = 1_500_000.0,
        totalExpense = 1_450_000.0,
        expensePercentage = 96.7
    ),
    BudgetExceeding(
        budgetId = 2L,
        categoryName = "Transportasi",
        startDate = "2025-07-01",
        endDate = "2025-07-31",
        amountLimit = 800_000.0,
        totalExpense = 760_000.0,
        expensePercentage = 95.0
    ),
    BudgetExceeding(
        budgetId = 3L,
        categoryName = "Hiburan",
        startDate = "2025-07-01",
        endDate = "2025-07-31",
        amountLimit = 500_000.0,
        totalExpense = 475_000.0,
        expensePercentage = 95.0
    )
)
