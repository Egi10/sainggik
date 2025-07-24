package id.buaja.sainggik.feature.transactions.main.component

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
import id.buaja.sainggik.core.designsystem.icon.collection.Error
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.initials
import id.buaja.sainggik.core.ui.MessageFeedback
import id.buaja.sainggik.core.ui.SwipeableListItem
import id.buaja.sainggik.domain.model.transactions.TransactionItem
import id.buaja.sainggik.feature.transactions.main.intent.OnTransactionsIntent
import id.buaja.sainggik.feature.transactions.main.intent.TransactionsIntent
import id.buaja.sainggik.feature.transactions.main.state.TransactionsListState
import org.jetbrains.compose.resources.stringResource
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.empty_no_transactions_desc
import sainggik.composeapp.generated.resources.empty_no_transactions_title
import sainggik.composeapp.generated.resources.error_fetch_transactions_desc
import sainggik.composeapp.generated.resources.error_load_transactions

@Composable
internal fun TransactionsSection(
    onTransactionsIntent: OnTransactionsIntent,
    state: TransactionsListState,
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
                MessageFeedback(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 24.dp,
                            horizontal = 16.dp
                        ),
                    image = SainggikIcon.Error,
                    title = stringResource(Res.string.error_load_transactions),
                    description = stringResource(Res.string.error_fetch_transactions_desc)
                )
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
                    title = stringResource(Res.string.empty_no_transactions_title),
                    description = stringResource(Res.string.empty_no_transactions_desc)
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
                                        top = 16.dp,
                                        bottom = 8.dp,
                                        start = 16.dp,
                                        end = 16.dp
                                    )
                            ) {
                                Text(
                                    text = group.dateLabel,
                                    style = SainggikTheme.typography.title,
                                    color = SainggikTheme.colors.onBackground
                                )
                            }
                        }

                        items(
                            items = group.items,
                            key = {
                                it.id
                            }
                        ) {
                            SwipeableListItem(
                                item = it,
                                onDelete = { item ->
                                    onTransactionsIntent.invoke(
                                        TransactionsIntent.OnDeleteTransactionsClick(
                                            id = item.id
                                        )
                                    )
                                },
                                onEdit = { item ->
                                    onTransactionsIntent.invoke(
                                        TransactionsIntent.OnEditTransactionsClick(
                                            id = item.id
                                        )
                                    )
                                },
                                content = { item ->
                                    TransactionsItem(
                                        item = item,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = SainggikTheme.colors.background
                                            )
                                            .padding(
                                                vertical = 13.5.dp,
                                                horizontal = 16.dp
                                            )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TransactionsItem(
    item: TransactionItem,
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
                text = item.category.initials(),
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
                text = item.title,
                style = SainggikTheme.typography.body,
                fontWeight = FontWeight.Medium,
                color = SainggikTheme.colors.onBackground
            )

            Text(
                text = item.category,
                style = SainggikTheme.typography.label,
                fontWeight = FontWeight.Normal,
                color = SainggikTheme.colors.onSurfaceVariant
            )
        }

        Text(
            text = item.amount,
            style = SainggikTheme.typography.body,
            fontWeight = FontWeight.Normal,
            color = SainggikTheme.colors.onBackground,
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterVertically
                )
        )
    }
}