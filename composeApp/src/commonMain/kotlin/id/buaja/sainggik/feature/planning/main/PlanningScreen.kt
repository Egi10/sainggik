package id.buaja.sainggik.feature.planning.main

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import id.buaja.sainggik.core.designsystem.components.SainggikFloatingActionButton
import id.buaja.sainggik.core.designsystem.components.SainggikScrollableTab
import id.buaja.sainggik.core.designsystem.components.SainggikTopAppBar
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.zeroInsets
import id.buaja.sainggik.feature.planning.budget.list.ListBudgetContent
import id.buaja.sainggik.feature.planning.budget.list.intent.OnBudgetListIntent
import id.buaja.sainggik.feature.planning.budget.list.state.ListBudgetState
import id.buaja.sainggik.feature.planning.category.CategoryPlanningContent
import id.buaja.sainggik.feature.planning.category.intent.OnCategoryIntent
import id.buaja.sainggik.feature.planning.category.state.CategoryState
import id.buaja.sainggik.feature.planning.main.intent.OnPlanningIntent
import id.buaja.sainggik.feature.planning.main.intent.PlanningIntent
import id.buaja.sainggik.feature.planning.main.state.PlanningState
import id.buaja.sainggik.feature.planning.summary.BudgetSummaryContent
import id.buaja.sainggik.feature.planning.summary.state.BudgetSummaryState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun PlanningScreen(
    state: PlanningState,
    categoryState: CategoryState,
    budgetSummaryState: BudgetSummaryState,
    listBudgetState: ListBudgetState,
    onPlanningIntent: OnPlanningIntent,
    onCategoryIntent: OnCategoryIntent,
    onBudgetListIntent: OnBudgetListIntent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = SainggikTheme.colors.background,
        contentWindowInsets = WindowInsets.zeroInsets,
        topBar = {
            SainggikTopAppBar(
                title = "Planning"
            )
        },
        floatingActionButton = {
            if (state.selectedTab == 0 || state.selectedTab == 1) {
                SainggikFloatingActionButton(
                    onClick = {
                        onPlanningIntent.invoke(PlanningIntent.FabClicked)
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            PlanningContent(
                state = state,
                categoryState = categoryState,
                budgetSummaryState = budgetSummaryState,
                listBudgetState = listBudgetState,
                onPlanningIntent = onPlanningIntent,
                onCategoryIntent = onCategoryIntent,
                onBudgetListIntent = onBudgetListIntent,
                modifier = Modifier
                    .padding(
                        paddingValues = paddingValues
                    )
            )
        }
    )
}

@Composable
private fun PlanningContent(
    state: PlanningState,
    categoryState: CategoryState,
    budgetSummaryState: BudgetSummaryState,
    listBudgetState: ListBudgetState,
    onPlanningIntent: OnPlanningIntent,
    onCategoryIntent: OnCategoryIntent,
    onBudgetListIntent: OnBudgetListIntent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SainggikScrollableTab(
            tabs = listOf(
                "Categories",
                "Budgets",
                "Summary"
            ),
            selectedTabIndex = state.selectedTab,
            onTabSelected = { tabIndex ->
                onPlanningIntent.invoke(PlanningIntent.ChangeTab(index = tabIndex))
            }
        )

        AnimatedContent(
            targetState = state.selectedTab,
            transitionSpec = {
                slideInHorizontally {
                    it
                } + fadeIn() togetherWith slideOutHorizontally {
                    -it
                } + fadeOut()
            },
            label = "TabTransition"
        ) { tab ->
            when (tab) {
                0 -> {
                    CategoryPlanningContent(
                        state = categoryState.listCategoryState,
                        onCategoryIntent = onCategoryIntent
                    )
                }

                1 -> {
                    ListBudgetContent(
                        state = listBudgetState,
                        onBudgetListIntent = onBudgetListIntent
                    )
                }

                2 -> {
                    BudgetSummaryContent(
                        state = budgetSummaryState
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PlanningScreenPreview() {
    SainggikTheme {
        PlanningScreen(
            state = PlanningState(),
            categoryState = CategoryState(),
            budgetSummaryState = BudgetSummaryState(),
            listBudgetState = ListBudgetState(),
            onPlanningIntent = {},
            onCategoryIntent = {},
            onBudgetListIntent = {}
        )
    }
}