package id.buaja.sainggik.feature.planning.budget.manage

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.ui.CalendarBottomSheets
import id.buaja.sainggik.core.ui.ChooseCategoryBottomSheet
import id.buaja.sainggik.feature.planning.budget.manage.intent.ManageBudgetIntent
import id.buaja.sainggik.feature.planning.budget.manage.navigation.ManageBudgetDestination
import id.buaja.sainggik.feature.planning.budget.manage.navigation.OnManageBudgetDestination
import id.buaja.sainggik.feature.planning.budget.manage.state.ManageBudgetSideEffect
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ManageBudgetRoute(
    onManageBudgetDestination: OnManageBudgetDestination,
    modifier: Modifier = Modifier,
    budgetId: Long? = null,
    viewModel: ManageBudgetViewModel = koinViewModel()
) {
    val state by viewModel.collectAsState()

    var showChooseCategoryBottomSheet by remember {
        mutableStateOf(false)
    }
    var showStartDateBottomSheet by remember {
        mutableStateOf(false)
    }
    var showEndDateBottomSheet by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.onIntent(
            intent = ManageBudgetIntent.Initial
        )
    }

    LaunchedEffect(budgetId) {
        if (budgetId != null) {
            viewModel.onIntent(
                intent = ManageBudgetIntent.InitialBudget(
                    budgetId = budgetId
                )
            )
        }
    }

    viewModel.collectSideEffect {
        when (it) {
            ManageBudgetSideEffect.NavigateBack -> {
                onManageBudgetDestination.invoke(ManageBudgetDestination.NavigationBack)
            }

            ManageBudgetSideEffect.ShowCategoryBottomSheet -> {
                showChooseCategoryBottomSheet = true
            }

            ManageBudgetSideEffect.ShowStartDateBottomSheet -> {
                showStartDateBottomSheet = true
            }

            ManageBudgetSideEffect.ShowEndDateBottomSheet -> {
                showEndDateBottomSheet = true
            }

            is ManageBudgetSideEffect.ShowToastMessage -> {
                // TODO
            }
        }
    }

    ManageBudgetScreen(
        modifier = modifier,
        budgetId = budgetId,
        budgetAmount = viewModel.amount,
        state = state,
        onManageBudgetIntent = viewModel::onIntent
    )

    if (showChooseCategoryBottomSheet) {
        ChooseCategoryBottomSheet(
            category = state.availableCategories,
            onDismissRequest = {
                showChooseCategoryBottomSheet = false
            },
            onSelectedItem = {
                viewModel.onIntent(
                    intent = ManageBudgetIntent.OnCategorySelected(it)
                )
            }
        )
    }

    if (showStartDateBottomSheet) {
        CalendarBottomSheets(
            onDismissRequest = {
                showStartDateBottomSheet = false
            },
            onDateSelected = {
                viewModel.onIntent(
                    intent = ManageBudgetIntent.OnStartDateSelected(it)
                )
            },
            selectedDate = state.startDate ?: DateTimeHelper.getCurrentLocalDate()
        )
    }

    if (showEndDateBottomSheet) {
        CalendarBottomSheets(
            onDismissRequest = {
                showEndDateBottomSheet = false
            },
            onDateSelected = {
                viewModel.onIntent(
                    intent = ManageBudgetIntent.OnEndDateSelected(it)
                )
            },
            selectedDate = state.endDate ?: DateTimeHelper.getCurrentLocalDate()
        )
    }
}