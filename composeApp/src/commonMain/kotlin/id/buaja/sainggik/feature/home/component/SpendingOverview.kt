package id.buaja.sainggik.feature.home.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.components.SainggikChips
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.utils.AppLocale
import id.buaja.sainggik.core.utils.formatCurrency
import id.buaja.sainggik.domain.model.transactions.TransactionSpending
import id.buaja.sainggik.feature.home.intent.HomeIntent
import id.buaja.sainggik.feature.home.intent.OnHomeIntent
import id.buaja.sainggik.feature.home.state.HomeFilterEnum
import id.buaja.sainggik.feature.home.state.HomeFilterState
import id.buaja.sainggik.feature.home.state.TransactionSpendingState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.*

@Composable
internal fun SpendingOverview(
    transactionSpendingState: TransactionSpendingState,
    homeFilterState: HomeFilterState?,
    onHomeIntent: OnHomeIntent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.title_spending_overview),
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

        when (transactionSpendingState) {
            TransactionSpendingState.Loading -> {
                // TODO
            }

            TransactionSpendingState.Empty -> {
                TransactionEmpty(
                    title = stringResource(Res.string.empty_no_spending_title),
                    description = stringResource(Res.string.empty_no_spending_desc),
                    modifier = Modifier
                        .padding(
                            all = 16.dp
                        )
                )
            }

            is TransactionSpendingState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TopSpending(
                        item = transactionSpendingState.data.first(),
                        onHomeIntent = onHomeIntent,
                        modifier = Modifier
                            .padding(
                                all = 16.dp
                            )
                    )

                    if (transactionSpendingState.data.size > 1) {
                        CategorySpending(
                            items = transactionSpendingState.data.drop(1),
                            filterState = homeFilterState,
                            modifier = Modifier
                                .padding(
                                    vertical = 24.dp,
                                    horizontal = 16.dp
                                )
                        )
                    }
                }
            }

            is TransactionSpendingState.Error -> {
                // TODO
            }
        }
    }
}

@Composable
private fun TopSpending(
    item: TransactionSpending,
    onHomeIntent: OnHomeIntent,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(Res.string.title_top_spending),
                    style = SainggikTheme.typography.label,
                    color = SainggikTheme.colors.secondary,
                    fontWeight = FontWeight.Normal
                )

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )

                Text(
                    text = buildAnnotatedString {
                        append(item.categoryName)
                        append(" - ")
                        append(
                            text = formatCurrency(
                                amount = item.totalExpense.toLong(),
                                locale = AppLocale.INDONESIA
                            )
                        )
                    },
                    style = SainggikTheme.typography.body,
                    color = SainggikTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )

                Text(
                    text = stringResource(
                        resource = Res.string.desc_spending_percentage,
                        formatArgs = arrayOf(
                            "${item.expensePercentage.toInt()}%",
                            formatCurrency(
                                amount = item.totalAllExpense.toLong(),
                                locale = AppLocale.INDONESIA
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
//                SainggikChips(
//                    selected = false,
//                    label = stringResource(Res.string.button_view_details),
//                    onClick = {
//                        onHomeIntent.invoke(HomeIntent.OnDetailSpendingOverviewClick)
//                    }
//                )
            }

            Image(
                painter = painterResource(Res.drawable.img_top_spending),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun CategorySpending(
    items: List<TransactionSpending>,
    filterState: HomeFilterState?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(Res.string.label_category_spending),
                style = SainggikTheme.typography.body,
                color = SainggikTheme.colors.onBackground,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    space = 24.dp
                )
            ) {
                items.forEach {
                    CategorySpendingBar(
                        name = it.categoryName,
                        amount = it.totalExpense.toFloat(),
                        maxAmount = it.totalAllExpense.toFloat(),
                        isAnimated = true
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(28.dp)
            )

            Text(
                text = stringResource(Res.string.desc_percentage_calculation, filterState?.value ?: ""),
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.secondary,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
private fun CategorySpendingBar(
    name: String,
    amount: Float,
    maxAmount: Float,
    isAnimated: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        targetValue = if (isAnimated) {
            amount / maxAmount
        } else {
            0f
        },
        animationSpec = tween(durationMillis = 1000),
        label = "bar_animation"
    )
    val percent by remember(animatedProgress) {
        derivedStateOf {
            (animatedProgress * 100).toInt()
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp
        )
    ) {
        Text(
            modifier = Modifier
                .weight(2.5f),
            text = buildAnnotatedString {
                append(name)
                append(' ')
                append(
                    formatCurrency(
                        amount = amount.toLong(),
                        locale = AppLocale.INDONESIA
                    )
                )
                append(' ')
                withStyle(
                    style = SpanStyle(
                        color = if (percent > 100) {
                            SainggikTheme.colors.error
                        } else {
                            SainggikTheme.colors.secondary
                        }
                    )
                ) {
                    append(
                        text = "($percent%)"
                    )
                }

            },
            style = SainggikTheme.typography.label,
            color = SainggikTheme.colors.secondary,
            fontWeight = FontWeight.Bold
        )

        HorizontalProgressBarCanvas(
            progress = animatedProgress,
            amount = amount,
            maxAmount = maxAmount,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun HorizontalProgressBarCanvas(
    progress: Float,
    modifier: Modifier = Modifier,
    barColor: Color = SainggikTheme.colors.surfaceVariant,
    lineColor: Color = SainggikTheme.colors.secondary,
    errorLineColor: Color = SainggikTheme.colors.error,
    maxAmount: Float = 1f,
    amount: Float = 0f
) {
    val clampedProgress = progress.coerceIn(0f, 1f)

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp)
    ) {
        val barWidth = size.width * clampedProgress
        val barHeight = size.height

        drawRect(
            color = barColor,
            topLeft = Offset(0f, 0f),
            size = Size(barWidth, barHeight)
        )

        if (clampedProgress > 0.01f) {
            val lineColorToUse = if (amount > maxAmount) {
                errorLineColor
            } else {
                lineColor
            }

            drawRect(
                color = lineColorToUse,
                topLeft = Offset(barWidth - 1.dp.toPx(), 0f),
                size = Size(2.dp.toPx(), barHeight)
            )
        }
    }
}

@Preview
@Composable
private fun FinancialOverviewPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            SpendingOverview(
                transactionSpendingState = TransactionSpendingState.Success(
                    data = listOf(
                        TransactionSpending(
                            categoryId = 1L,
                            categoryName = "Food & Beverage",
                            totalExpense = 1200.0,
                            expensePercentage = 25.0,
                            totalAllExpense = 5000.0
                        ),
                        TransactionSpending(
                            categoryId = 2L,
                            categoryName = "Transportation",
                            totalExpense = 800.0,
                            expensePercentage = 16.0,
                            totalAllExpense = 5000.0
                        ),
                        TransactionSpending(
                            categoryId = 3L,
                            categoryName = "Entertainment",
                            totalExpense = 500.0,
                            expensePercentage = 10.0,
                            totalAllExpense = 5000.0
                        ),
                        TransactionSpending(
                            categoryId = 4L,
                            categoryName = "Utilities",
                            totalExpense = 1500.0,
                            expensePercentage = 30.0,
                            totalAllExpense = 5000.0
                        )
                    ).sortedByDescending {
                        it.totalExpense
                    },
                ),
                homeFilterState = HomeFilterState(
                    key = HomeFilterEnum.THIS_WEEK,
                    value = "Minggu ini"
                ),
                onHomeIntent = {}
            )
        }
    }
}