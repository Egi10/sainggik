package id.buaja.sainggik.feature.planning.budget.manage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.designsystem.components.SainggikButton
import id.buaja.sainggik.core.designsystem.components.SainggikSelectableTextField
import id.buaja.sainggik.core.designsystem.components.SainggikTextField
import id.buaja.sainggik.core.designsystem.components.SainggikTopAppBar
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Calendar
import id.buaja.sainggik.core.designsystem.icon.collection.Dropdown
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.zeroInsets
import id.buaja.sainggik.core.ui.transformations.CurrencyVisualTransformation
import id.buaja.sainggik.feature.planning.budget.manage.intent.ManageBudgetIntent
import id.buaja.sainggik.feature.planning.budget.manage.intent.OnManageBudgetIntent
import id.buaja.sainggik.feature.planning.budget.manage.state.ManageBudgetState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.*

@Composable
internal fun ManageBudgetScreen(
    budgetAmount: String,
    state: ManageBudgetState,
    onManageBudgetIntent: OnManageBudgetIntent,
    modifier: Modifier = Modifier,
    budgetId: Long? = null
) {
    Scaffold(
        modifier = modifier,
        containerColor = SainggikTheme.colors.background,
        contentWindowInsets = WindowInsets.zeroInsets,
        topBar = {
            SainggikTopAppBar(
                title = if (budgetId != null) {
                    stringResource(Res.string.title_edit_budget)
                } else {
                    stringResource(Res.string.title_add_budget)
                },
                onBackClick = {
                    onManageBudgetIntent.invoke(ManageBudgetIntent.OnBackClick)
                }
            )
        },
        content = { paddingValues ->
            ManageBudgetContent(
                budgetId = budgetId,
                budgetAmount = budgetAmount,
                state = state,
                onManageBudgetIntent = onManageBudgetIntent,
                modifier = Modifier
                    .padding(
                        paddingValues = paddingValues
                    )
            )
        }
    )
}

@Composable
private fun ManageBudgetContent(
    budgetId: Long?,
    budgetAmount: String,
    state: ManageBudgetState,
    onManageBudgetIntent: OnManageBudgetIntent,
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
                )
        ) {
            SainggikSelectableTextField(
                value = state.category?.name.orEmpty(),
                label = stringResource(Res.string.label_category),
                placeholder = stringResource(Res.string.placeholder_select_category),
                trailingIcon = SainggikIcon.Dropdown,
                enabled = budgetId == null,
                onTrailingIconClick = {
                    onManageBudgetIntent.invoke(ManageBudgetIntent.OnCategoryClick)
                }
            )

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )

            SainggikTextField(
                label = stringResource(Res.string.label_budget_amount),
                placeholder = stringResource(Res.string.placeholder_budget_amount),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                value = budgetAmount,
                visualTransformation = CurrencyVisualTransformation(),
                onValueChange = {
                    onManageBudgetIntent.invoke(
                        ManageBudgetIntent.OnBudgetAmountChange(it)
                    )
                }
            )

            Spacer(
                modifier = Modifier
                    .height(28.dp)
            )

            Text(
                text = stringResource(Res.string.label_period),
                style = SainggikTheme.typography.title,
                fontWeight = FontWeight.Bold,
                color = SainggikTheme.colors.onBackground
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            SainggikSelectableTextField(
                value = state.startDate?.let {
                    DateTimeHelper.formatDate(
                        date = it,
                        outputFormat = DateTimeHelper.Formats.DATE_SHORT
                    )
                } ?: "",
                label = stringResource(Res.string.label_start_date),
                placeholder = stringResource(Res.string.placeholder_start_date),
                trailingIcon = SainggikIcon.Calendar,
                onTrailingIconClick = {
                    onManageBudgetIntent.invoke(
                        ManageBudgetIntent.OnStartDateClick
                    )
                }
            )

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )

            SainggikSelectableTextField(
                value = state.endDate?.let {
                    DateTimeHelper.formatDate(
                        date = it,
                        outputFormat = DateTimeHelper.Formats.DATE_SHORT
                    )
                } ?: "",
                label = stringResource(Res.string.label_end_date),
                placeholder = stringResource(Res.string.placeholder_end_date),
                trailingIcon = SainggikIcon.Calendar,
                onTrailingIconClick = {
                    onManageBudgetIntent.invoke(
                        ManageBudgetIntent.OnEndDateClick
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
            text = if (budgetId == null) {
                stringResource(Res.string.button_save_budget)
            } else {
                stringResource(Res.string.title_edit_budget)
            },
            enable = budgetAmount.isNotEmpty()
                    && state.category != null
                    && state.endDate != null
                    && state.startDate != null,
            onClick = {
                onManageBudgetIntent.invoke(
                    ManageBudgetIntent.OnSubmitClick
                )
            }
        )
    }
}

@Preview
@Composable
private fun ManageBudgetScreenPreview() {
    SainggikTheme {
        ManageBudgetScreen(
            budgetAmount = "",
            state = ManageBudgetState(),
            onManageBudgetIntent = {}
        )
    }
}
