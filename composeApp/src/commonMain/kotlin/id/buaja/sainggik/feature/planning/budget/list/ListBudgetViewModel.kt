package id.buaja.sainggik.feature.planning.budget.list

import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.domain.usecase.budgets.DeleteBudgetUseCase
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetsWithCategoryUseCase
import id.buaja.sainggik.feature.planning.budget.list.intent.BudgetListIntent
import id.buaja.sainggik.feature.planning.budget.list.state.ListBudgetListSideEffect
import id.buaja.sainggik.feature.planning.budget.list.state.ListBudgetState
import kotlinx.coroutines.flow.collectLatest

class ListBudgetViewModel(
    private val getBudgetsWithCategoryUseCase: GetBudgetsWithCategoryUseCase,
    private val deleteBudgetUseCase: DeleteBudgetUseCase
) : BaseViewModel<ListBudgetState, ListBudgetListSideEffect>(initialState = ListBudgetState()) {

    fun onIntent(intent: BudgetListIntent) {
        when (intent) {
            BudgetListIntent.Initial -> {
                getBudget()
            }

            is BudgetListIntent.DeleteBudget -> {
                deleteBudget(
                    id = intent.id
                )
            }

            is BudgetListIntent.EditBudget -> {
                sideEffect {
                    ListBudgetListSideEffect.NavigateToEditBudget(
                        id = intent.id
                    )
                }
            }
        }
    }

    private fun getBudget() = intent {
        getBudgetsWithCategoryUseCase.invoke()
            .collectLatest { result ->
                val newCategoryState = when (result) {
                    Result.Loading -> state.copy(
                        isLoading = true,
                        errorMessage = null
                    )

                    is Result.Success -> state.copy(
                        isLoading = false,
                        data = result.data,
                        errorMessage = null
                    )

                    is Result.Error -> state.copy(
                        isLoading = false,
                        errorMessage = result.exception.message
                    )
                }

                reduce {
                    newCategoryState
                }
            }
    }

    private fun deleteBudget(id: Long) = intent {
        val result = deleteBudgetUseCase.invoke(
            id = id
        )

        when (result) {
            Result.Loading -> {}
            is Result.Success -> {}

            is Result.Error -> {
                // TODO : Show Error
            }
        }
    }
}