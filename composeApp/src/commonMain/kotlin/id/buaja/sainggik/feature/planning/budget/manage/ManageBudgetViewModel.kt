package id.buaja.sainggik.feature.planning.budget.manage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.domain.model.budgets.BudgetUpdateParam
import id.buaja.sainggik.domain.model.budgets.BudgetsParam
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetByIdUseCase
import id.buaja.sainggik.domain.usecase.budgets.SetBudgetsUseCase
import id.buaja.sainggik.domain.usecase.budgets.UpdateBudgetUseCase
import id.buaja.sainggik.domain.usecase.categories.GetCategoriesUseCase
import id.buaja.sainggik.feature.planning.budget.manage.intent.ManageBudgetIntent
import id.buaja.sainggik.feature.planning.budget.manage.state.ManageBudgetSideEffect
import id.buaja.sainggik.feature.planning.budget.manage.state.ManageBudgetState
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.getString
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.error_load_category_list
import sainggik.composeapp.generated.resources.error_save_budget

class ManageBudgetViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val setBudgetsUseCase: SetBudgetsUseCase,
    private val updateBudgetUseCase: UpdateBudgetUseCase,
    private val getBudgetByIdUseCase: GetBudgetByIdUseCase
) : BaseViewModel<ManageBudgetState, ManageBudgetSideEffect>(initialState = ManageBudgetState()) {

    private var budgetId by mutableStateOf<Long?>(null)
    var amount by mutableStateOf("")
        private set

    fun onIntent(intent: ManageBudgetIntent) {
        when (intent) {
            ManageBudgetIntent.Initial -> {
                getCategories()
            }

            is ManageBudgetIntent.InitialBudget -> {
                budgetId = intent.budgetId
                getBudgetById(
                    id = intent.budgetId
                )
            }

            ManageBudgetIntent.OnBackClick -> {
                sideEffect {
                    ManageBudgetSideEffect.NavigateBack
                }
            }

            ManageBudgetIntent.OnCategoryClick -> {
                sideEffect {
                    ManageBudgetSideEffect.ShowCategoryBottomSheet
                }
            }

            is ManageBudgetIntent.OnCategorySelected -> {
                updateState {
                    copy(
                        category = intent.category
                    )
                }
            }

            is ManageBudgetIntent.OnBudgetAmountChange -> {
                amount = intent.amount
            }

            ManageBudgetIntent.OnEndDateClick -> {
                sideEffect {
                    ManageBudgetSideEffect.ShowEndDateBottomSheet
                }
            }

            is ManageBudgetIntent.OnEndDateSelected -> {
                updateState {
                    copy(
                        endDate = intent.date
                    )
                }
            }

            ManageBudgetIntent.OnStartDateClick -> {
                sideEffect {
                    ManageBudgetSideEffect.ShowStartDateBottomSheet
                }
            }

            is ManageBudgetIntent.OnStartDateSelected -> {
                updateState {
                    copy(
                        startDate = intent.date
                    )
                }
            }

            ManageBudgetIntent.OnSubmitClick -> {
                budgetId?.let {
                    updateBudget(id = it)
                } ?: setBudget()
            }
        }
    }

    private fun getCategories() = intent {
        getCategoriesUseCase()
            .collectLatest { result ->
                when (result) {
                    Result.Loading -> {}

                    is Result.Success -> {
                        reduce {
                            state.copy(
                                availableCategories = result.data
                            )
                        }
                    }

                    is Result.Error -> {
                        postSideEffect(
                            sideEffect = ManageBudgetSideEffect.ShowToastMessage(
                                message = getString(Res.string.error_load_category_list)
                            )
                        )
                    }
                }
            }
    }

    private fun setBudget() = intent {
        val result = setBudgetsUseCase.invoke(
            param = BudgetsParam(
                categoryId = state.category?.id ?: 0,
                startDate = state.startDate.toString(),
                endDate = state.endDate.toString(),
                amountLimit = amount.toDouble()
            )
        )

        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                postSideEffect(
                    sideEffect = ManageBudgetSideEffect.NavigateBack
                )
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = ManageBudgetSideEffect.ShowToastMessage(
                        message = getString(Res.string.error_save_budget)
                    )
                )
            }
        }
    }

    private fun updateBudget(id: Long) = intent {
        val result = updateBudgetUseCase.invoke(
            param = BudgetUpdateParam(
                startDate = state.startDate.toString(),
                endDate = state.endDate.toString(),
                amountLimit = amount.toDouble(),
                id =  id
            )
        )

        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                postSideEffect(
                    sideEffect = ManageBudgetSideEffect.NavigateBack
                )
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = ManageBudgetSideEffect.ShowToastMessage(
                        message = getString(Res.string.error_save_budget)
                    )
                )
            }
        }
    }

    private fun getBudgetById(id: Long) = intent {
        val result = getBudgetByIdUseCase.invoke(
            id = id
        )

        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                val data = result.data

                amount = data.amountLimit.toLong().toString()
                Logger.d {
                    "Disini ${data.amountLimit}"
                }
                reduce {
                    state.copy(
                        category = state.availableCategories.find {
                            it.id == result.data.categoryId
                        },
                        startDate = DateTimeHelper.parseToLocalDate(
                            date = data.startDate,
                            format = DateTimeHelper.Formats.DATE
                        ),
                        endDate = DateTimeHelper.parseToLocalDate(
                            date = data.endDate,
                            format = DateTimeHelper.Formats.DATE
                        )
                    )
                }
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = ManageBudgetSideEffect.ShowToastMessage(
                        message = "Gagal mendapatkan transaksi"
                    )
                )
            }
        }
    }
}