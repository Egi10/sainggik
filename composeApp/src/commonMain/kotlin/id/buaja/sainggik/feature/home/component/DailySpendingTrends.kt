package id.buaja.sainggik.feature.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.utils.AppLocale
import id.buaja.sainggik.core.utils.formatCurrency
import id.buaja.sainggik.domain.model.transactions.TransactionDailySpending
import id.buaja.sainggik.domain.model.transactions.TransactionsRecentDaily
import id.buaja.sainggik.feature.home.state.TransactionDailySpendingState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.*

@Composable
internal fun DailySpendingTrends(
    transactionDailySpendingState: TransactionDailySpendingState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.title_recent_spending_trend),
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

        when (transactionDailySpendingState) {
            TransactionDailySpendingState.Loading -> {
                // TODO
            }

            TransactionDailySpendingState.Empty -> {
                TransactionEmpty(
                    title = stringResource(Res.string.empty_no_daily_spending_title),
                    description = stringResource(Res.string.empty_no_daily_spending_desc),
                    modifier = Modifier
                        .padding(
                            all = 16.dp
                        )
                )
            }

            is TransactionDailySpendingState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    WeeklyBarChart(
                        bars = transactionDailySpendingState.data.daily.let { dailyList ->
                            val maxExpense = dailyList.maxOfOrNull { it.totalExpanses }
                                .takeIf { it != null && it > 0.0 } ?: 1.0

                            dailyList.map { day ->
                                WeeklyBar(
                                    day = DateTimeHelper.formatDate(
                                        date = day.date,
                                        inputFormat = DateTimeHelper.Formats.DATE,
                                        outputFormat = DateTimeHelper.Formats.DAY_WEEK
                                    ),
                                    heightRatio = (day.totalExpanses / maxExpense).toFloat()
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(
                                vertical = 24.dp,
                                horizontal = 16.dp
                            )
                    )

                    Text(
                        text = stringResource(
                            resource = Res.string.desc_average_daily_spending,
                            formatArgs = arrayOf(
                                formatCurrency(
                                    amount = transactionDailySpendingState.data.averageSpending.toLong(),
                                    locale = AppLocale.INDONESIA
                                )
                            )
                        ),
                        style = SainggikTheme.typography.label,
                        fontWeight = FontWeight.Normal,
                        color = SainggikTheme.colors.secondary,
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 4.dp,
                                bottom = 12.dp
                            )
                    )
                }
            }

            is TransactionDailySpendingState.Error -> {
                // TODO
            }
        }
    }
}

@Composable
fun WeeklyBarChart(
    bars: List<WeeklyBar>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        bars.forEach { bar ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .weight(1f)
            ) {
                BarChartCanvas(
                    heightRatio = bar.heightRatio
                )

                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                )

                Text(
                    text = bar.day,
                    style = SainggikTheme.typography.label,
                    color = SainggikTheme.colors.secondary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun BarChartCanvas(
    heightRatio: Float,
    modifier: Modifier = Modifier,
    barColor: Color = SainggikTheme.colors.surfaceVariant,
    lineColor: Color = SainggikTheme.colors.secondary
) {
    val validRatio = heightRatio.coerceIn(0f, 1f)

    Canvas(
        modifier = modifier
            .size(
                width = 28.dp,
                height = 137.dp
            )
    ) {
        val barHeight = size.height * validRatio
        val barWidth = size.width

        // Bar
        drawRect(
            color = barColor,
            topLeft = Offset(0f, size.height - barHeight),
            size = Size(
                width = barWidth,
                height = barHeight
            )
        )

        // Divider Top
        drawRect(
            color = lineColor,
            topLeft = Offset(
                x = 0f,
                y = size.height - barHeight
            ),
            size = Size(
                width = barWidth,
                height = 2.dp.toPx()
            )
        )
    }
}

data class WeeklyBar(
    val day: String,
    val heightRatio: Float
)

@Preview
@Composable
private fun DailySpendingTrendsPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            DailySpendingTrends(
                transactionDailySpendingState = TransactionDailySpendingState.Success(
                    data = dummyTransactionsRecentDaily
                )
            )
        }

    }
}

private val dummyTransactionsRecentDaily = TransactionsRecentDaily(
    averageSpending = 152340.75,
    daily = listOf(
        TransactionDailySpending(
            date = "2025-07-08",
            totalExpanses = 120000.0
        ),
        TransactionDailySpending(
            date = "2025-07-09",
            totalExpanses = 175000.0
        ),
        TransactionDailySpending(
            date = "2025-07-10",
            totalExpanses = 200000.0
        ),
        TransactionDailySpending(
            date = "2025-07-11",
            totalExpanses = 0.0 // Tidak ada pengeluaran hari ini
        ),
        TransactionDailySpending(
            date = "2025-07-12",
            totalExpanses = 95000.0
        ),
        TransactionDailySpending(
            date = "2025-07-13",
            totalExpanses = 130000.0
        ),
        TransactionDailySpending(
            date = "2025-07-14",
            totalExpanses = 175000.0
        )
    )
)
