package id.buaja.sainggik.feature.transactions.manage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.designsystem.components.*
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Calendar
import id.buaja.sainggik.core.designsystem.icon.collection.Dropdown
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.zeroInsets
import id.buaja.sainggik.core.ui.transformations.CurrencyVisualTransformation
import id.buaja.sainggik.feature.transactions.manage.intent.ManageTransactionIntent
import id.buaja.sainggik.feature.transactions.manage.intent.OnManageTransactionIntent
import id.buaja.sainggik.feature.transactions.manage.state.ManageTransactionState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ManageTransactionScreen(
    amount: String,
    note: String,
    state: ManageTransactionState,
    isVisibleTransaction: Boolean,
    onManageTransactionIntent: OnManageTransactionIntent,
    modifier: Modifier = Modifier,
    transactionId: Long? = null,
) {
    Scaffold(
        modifier = modifier,
        containerColor = SainggikTheme.colors.background,
        contentWindowInsets = WindowInsets.zeroInsets,
        topBar = {
            SainggikTopAppBar(
                title = if (transactionId != null) {
                    "Edit Transaction"
                } else {
                    "New Transaction"
                },
                onBackClick = {
                    onManageTransactionIntent.invoke(
                        ManageTransactionIntent.OnBackClick
                    )
                }
            )
        },
        content = { paddingValues ->
            ManageTransactionContent(
                amount = amount,
                note = note,
                state = state,
                onManageTransactionIntent = onManageTransactionIntent,
                isVisibleTransaction = isVisibleTransaction,
                transactionsId = transactionId,
                modifier = Modifier
                    .padding(
                        paddingValues = paddingValues
                    )
            )
        }
    )
}

@Composable
private fun ManageTransactionContent(
    amount: String,
    note: String,
    state: ManageTransactionState,
    isVisibleTransaction: Boolean,
    transactionsId: Long?,
    onManageTransactionIntent: OnManageTransactionIntent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 24.dp
            )
        ) {
            SainggikTextField(
                label = "Amount",
                placeholder = "0.00",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                value = amount,
                visualTransformation = CurrencyVisualTransformation(),
                onValueChange = {
                    onManageTransactionIntent.invoke(
                        ManageTransactionIntent.OnAmountChanged(it)
                    )
                }
            )

            SainggikSelectableTextField(
                value = state.selectedCategory?.name.orEmpty(),
                label = "Category",
                placeholder = "Select Category",
                trailingIcon = SainggikIcon.Dropdown,
                onTrailingIconClick = {
                    onManageTransactionIntent.invoke(
                        ManageTransactionIntent.OnCategoryClick
                    )
                }
            )

            SainggikSelectableTextField(
                value = DateTimeHelper.formatDate(
                    date = state.date,
                    outputFormat = DateTimeHelper.Formats.DATE_SHORT
                ),
                label = "Date",
                placeholder = "Select Date",
                trailingIcon = SainggikIcon.Calendar,
                onTrailingIconClick = {
                    onManageTransactionIntent.invoke(
                        ManageTransactionIntent.OnDateClick
                    )
                }
            )

            SainggikTextAreaField(
                label = "Note",
                placeholder = "Add Note",
                value = note,
                onValueChange = {
                    onManageTransactionIntent.invoke(
                        ManageTransactionIntent.OnNoteChanged(it)
                    )
                }
            )
        }

        SainggikButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                ),
            text = if (transactionsId == null) {
                "Save Transaction"
            } else {
                "Edit Transaction"
            },
            enable = isVisibleTransaction,
            onClick = {
                onManageTransactionIntent.invoke(
                    ManageTransactionIntent.OnSubmitClick
                )
            }
        )
    }
}

@Preview
@Composable
private fun AddTransactionScreenPreview() {
    SainggikTheme {
        ManageTransactionScreen(
            amount = "",
            note = "",
            state = ManageTransactionState(),
            isVisibleTransaction = false,
            onManageTransactionIntent = { }
        )
    }
}