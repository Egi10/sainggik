package id.buaja.sainggik.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.utils.AppLocale
import id.buaja.sainggik.core.utils.formatCurrency
import id.buaja.sainggik.feature.home.state.HomeFilterEnum
import id.buaja.sainggik.feature.home.state.HomeFilterState
import id.buaja.sainggik.feature.home.state.TransactionSummaryState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.desc_remaining_balance
import sainggik.composeapp.generated.resources.desc_total_expense
import sainggik.composeapp.generated.resources.desc_total_income
import sainggik.composeapp.generated.resources.img_remaining_budge
import sainggik.composeapp.generated.resources.img_saved
import sainggik.composeapp.generated.resources.img_total_spent
import sainggik.composeapp.generated.resources.label_remaining_balance
import sainggik.composeapp.generated.resources.label_total_expense
import sainggik.composeapp.generated.resources.label_total_income

@Composable
internal fun TransactionOverview(
    transactionSummaryState: TransactionSummaryState,
    filterSelectable: HomeFilterState?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = filterSelectable?.value.orEmpty(),
            style = SainggikTheme.typography.title,
            color = SainggikTheme.colors.onBackground,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
        )

        TransactionOverviewItem(
            label = stringResource(Res.string.label_total_income),
            value = formatCurrency(
                amount = transactionSummaryState.data?.totalIncome?.toLong() ?: 0,
                locale = AppLocale.INDONESIA
            ),
            description = stringResource(
                resource = Res.string.desc_total_income,
                formatArgs = arrayOf(
                    filterSelectable?.value.orEmpty()
                )
            ),
            image = painterResource(Res.drawable.img_total_spent),
            modifier = Modifier
                .fillMaxWidth()
        )

        TransactionOverviewItem(
            label = stringResource(Res.string.label_total_expense),
            value = formatCurrency(
                amount = transactionSummaryState.data?.totalExpense?.toLong() ?: 0,
                locale = AppLocale.INDONESIA
            ),
            description = stringResource(
                resource = Res.string.desc_total_expense,
                formatArgs = arrayOf(
                    filterSelectable?.value.orEmpty()
                )
            ),
            image = painterResource(Res.drawable.img_remaining_budge),
            modifier = Modifier
                .fillMaxWidth()
        )

        TransactionOverviewItem(
            label = stringResource(Res.string.label_remaining_balance),
            value = formatCurrency(
                amount = transactionSummaryState.data?.remainingBalance?.toLong() ?: 0,
                locale = AppLocale.INDONESIA
            ),
            description = stringResource(
                resource = Res.string.desc_remaining_balance,
                formatArgs = arrayOf(
                    filterSelectable?.value.orEmpty()
                )
            ),
            image = painterResource(Res.drawable.img_saved),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun TransactionOverviewItem(
    label: String,
    value: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(
                all = 16.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    space = 4.dp
                )
            ) {
                Text(
                    text = label,
                    style = SainggikTheme.typography.label,
                    fontWeight = FontWeight.Normal,
                    color = SainggikTheme.colors.secondary
                )

                Text(
                    text = value,
                    style = SainggikTheme.typography.body,
                    fontWeight = FontWeight.Bold,
                    color = SainggikTheme.colors.onBackground
                )

                Text(
                    text = description,
                    style = SainggikTheme.typography.label,
                    fontWeight = FontWeight.Normal,
                    color = SainggikTheme.colors.secondary
                )
            }

            Image(
                painter = image,
                contentDescription = label
            )
        }
    }
}

@Preview
@Composable
private fun TransactionOverviewPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            TransactionOverview(
                filterSelectable = HomeFilterState(
                    key = HomeFilterEnum.THIS_WEEK,
                    value = "Minggu ini"
                ),
                transactionSummaryState = TransactionSummaryState()
            )
        }
    }
}