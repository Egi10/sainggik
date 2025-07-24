package id.buaja.sainggik.feature.planning.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import id.buaja.sainggik.feature.planning.budget.list.ListBudgetViewModel
import id.buaja.sainggik.feature.planning.budget.list.intent.BudgetListIntent
import id.buaja.sainggik.feature.planning.budget.list.state.ListBudgetListSideEffect
import id.buaja.sainggik.feature.planning.category.CategoryViewModel
import id.buaja.sainggik.feature.planning.category.components.bottomsheet.ManageCategoryBottomSheet
import id.buaja.sainggik.feature.planning.category.intent.CategoryIntent
import id.buaja.sainggik.feature.planning.category.state.CategorySideEffect
import id.buaja.sainggik.feature.planning.main.navigation.OnPlanningDestination
import id.buaja.sainggik.feature.planning.main.navigation.PlanningDestination
import id.buaja.sainggik.feature.planning.main.state.PlanningSideEffect
import id.buaja.sainggik.feature.planning.summary.BudgetSummaryViewModel
import id.buaja.sainggik.feature.planning.summary.intent.BudgetSummaryIntent
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlanningRoute(
    onPlanningDestination: OnPlanningDestination,
    modifier: Modifier = Modifier,
    viewModel: PlanningViewModel = koinViewModel(),
    categoryViewModel: CategoryViewModel = koinViewModel(),
    budgetSummaryViewModel: BudgetSummaryViewModel = koinViewModel(),
    listBudgetViewModel: ListBudgetViewModel = koinViewModel()
) {
    val state by viewModel.collectAsState()
    val categoryState by categoryViewModel.collectAsState()
    val budgetSummaryState by budgetSummaryViewModel.collectAsState()
    val budgetState by listBudgetViewModel.collectAsState()

    var showCategoryBottomSheet by remember { mutableStateOf(false) }

    viewModel.collectSideEffect {
        when (it) {
            PlanningSideEffect.ShowAddCategoryBottomSheet -> {
                showCategoryBottomSheet = true
            }

            PlanningSideEffect.LoadCategoryData -> {
                categoryViewModel.onIntent(
                    intent = CategoryIntent.Initial
                )
            }

            PlanningSideEffect.LoadSummary -> {
                budgetSummaryViewModel.onIntent(
                    intent = BudgetSummaryIntent.Initial
                )
            }

            PlanningSideEffect.LoadBudget -> {
                listBudgetViewModel.onIntent(
                    intent = BudgetListIntent.Initial
                )
            }

            is PlanningSideEffect.NavigateManageBudget -> {
                onPlanningDestination.invoke(PlanningDestination.NavigationToManageBudget(it.budgetId))
            }
        }
    }

    categoryViewModel.collectSideEffect {
        when (it) {
            is CategorySideEffect.ShowAddCategoryToast -> {
                // TODO Toast
                showCategoryBottomSheet = false
            }

            CategorySideEffect.ShowUpdateCategoryBottomSheet -> {
                showCategoryBottomSheet = true
            }
        }
    }

    listBudgetViewModel.collectSideEffect {
        when (it) {
            is ListBudgetListSideEffect.NavigateToEditBudget -> {
                onPlanningDestination.invoke(PlanningDestination.NavigationToManageBudget(budgetId = it.id))
            }
        }
    }

    PlanningScreen(
        state = state,
        categoryState = categoryState,
        budgetSummaryState = budgetSummaryState,
        listBudgetState = budgetState,
        onPlanningIntent = viewModel::onIntent,
        onCategoryIntent = categoryViewModel::onIntent,
        onBudgetListIntent = listBudgetViewModel::onIntent,
        modifier = modifier
    )

    if (showCategoryBottomSheet) {
        ManageCategoryBottomSheet(
            name = categoryViewModel.name,
            manageCategoryState = categoryState.manageCategoryState,
            onCategoryIntent = categoryViewModel::onIntent,
            onDismissRequest = {
                showCategoryBottomSheet = false
            }
        )
    }
}