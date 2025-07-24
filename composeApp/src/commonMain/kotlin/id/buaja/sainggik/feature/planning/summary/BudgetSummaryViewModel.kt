package id.buaja.sainggik.feature.planning.summary

import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetSummariesUseCase
import id.buaja.sainggik.feature.planning.summary.intent.BudgetSummaryIntent
import id.buaja.sainggik.feature.planning.summary.state.BudgetSummaryState
import kotlinx.coroutines.flow.collectLatest

class BudgetSummaryViewModel(
    private val getBudgetSummariesUseCase: GetBudgetSummariesUseCase
) : BaseViewModel<BudgetSummaryState, Nothing>(initialState = BudgetSummaryState()) {

    fun onIntent(intent: BudgetSummaryIntent) {
        when (intent) {
            BudgetSummaryIntent.Initial -> {
                getSummaries()
            }
        }
    }

    private fun getSummaries() = intent {
        getBudgetSummariesUseCase.invoke()
            .collectLatest {
                when (it) {
                    Result.Loading -> {
                        reduce {
                            state.copy(
                                isLoading = true,
                                errorMessage = null
                            )
                        }
                    }

                    is Result.Success -> {
                        reduce {
                            state.copy(
                                isLoading = false,
                                errorMessage = null,
                                data = it.data
                            )
                        }
                    }

                    is Result.Error -> {
                        reduce {
                            state.copy(
                                isLoading = true,
                                errorMessage = "Gagal"
                            )
                        }
                    }
                }
            }
    }
}