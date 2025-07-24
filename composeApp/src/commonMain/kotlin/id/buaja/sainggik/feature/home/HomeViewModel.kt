package id.buaja.sainggik.feature.home

import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetsExceedingThresholdUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetRecentDailyTransactionSpendingUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsSpendingByDateRangeUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsSummaryByDateRangeUseCase
import id.buaja.sainggik.feature.home.intent.HomeIntent
import id.buaja.sainggik.feature.home.state.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus

class HomeViewModel(
    private val getTransactionsSummaryByDateRangeUseCase: GetTransactionsSummaryByDateRangeUseCase,
    private val getTransactionsSpendingByDateRangeUseCase: GetTransactionsSpendingByDateRangeUseCase,
    private val getRecentDailyTransactionSpendingUseCase: GetRecentDailyTransactionSpendingUseCase,
    private val getBudgetsExceedingThresholdUseCase: GetBudgetsExceedingThresholdUseCase
) : BaseViewModel<HomeState, HomeSideEffect>(initialState = HomeState()) {

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.InitialFilter -> {
                updateState {
                    copy(
                        filter = intent.filters,
                        filterSelected = intent.filters[0]
                    )
                }
                handleFilterSelection(
                    filter = intent.filters[0]
                )

                getTransactionDailySpending()
                getBudgetsExceeding()
            }

            is HomeIntent.OnChangeFilter -> {
                handleFilterSelection(
                    filter = intent.filter
                )
            }

            HomeIntent.OnDetailSpendingOverviewClick -> {
                // TODO
            }

            HomeIntent.OnDetailBudgetAlertClick -> {
                // TODO
            }
        }
    }

    private fun handleFilterSelection(
        filter: HomeFilterState
    ) {
        when (filter.key) {
            HomeFilterEnum.THIS_WEEK -> {
                updateState {
                    copy(
                        filterSelected = filter
                    )
                }

                val (startDate, endDate) = getThisWeekDateRange()
                loadTransactionData(
                    startDate = startDate,
                    endDate = endDate
                )
            }

            HomeFilterEnum.THIS_MONTH -> {
                updateState {
                    copy(
                        filterSelected = filter
                    )
                }

                val (startDate, endDate) = getThisMonthDateRange()
                loadTransactionData(
                    startDate = startDate,
                    endDate = endDate
                )
            }

            HomeFilterEnum.CUSTOM -> {
                // TODO Show bottom sheet
            }
        }
    }

    private fun getThisWeekDateRange(): Pair<String, String> {
        val dateNow = DateTimeHelper.getCurrentLocalDate()
        val dayOfWeek = dateNow.dayOfWeek.ordinal // Monday = 0, Sunday = 6
        val startOfWeek = dateNow.minus(dayOfWeek, DateTimeUnit.DAY)

        return startOfWeek.toString() to dateNow.toString()
    }

    private fun getThisMonthDateRange(): Pair<String, String> {
        val dateNow = DateTimeHelper.getCurrentLocalDate()
        val startOfMonth = dateNow.minus(
            value = 1,
            unit = DateTimeUnit.MONTH
        ).toString()

        return startOfMonth to dateNow.toString()
    }

    private fun loadTransactionData(startDate: String, endDate: String) {
        getTransactionSummary(
            startDate = startDate,
            endDate = endDate
        )
        getTransactionSpending(
            startDate = startDate,
            endDate = endDate
        )
    }

    private fun getTransactionSummary(
        startDate: String,
        endDate: String
    ) = intent {
        getTransactionsSummaryByDateRangeUseCase.invoke(
            startDate = startDate,
            endDate = endDate
        ).collectLatest {
            val state = when (it) {
                Result.Loading -> {
                    state.copy(
                        transactionSummaryState = TransactionSummaryState(
                            isLoading = true
                        )
                    )
                }

                is Result.Success -> {
                    state.copy(
                        transactionSummaryState = TransactionSummaryState(
                            isLoading = false,
                            data = it.data
                        )
                    )
                }

                is Result.Error -> {
                    state.copy(
                        transactionSummaryState = TransactionSummaryState(
                            isLoading = false
                        )
                    )
                }
            }

            reduce {
                state
            }
        }
    }

    private fun getTransactionSpending(
        startDate: String,
        endDate: String
    ) = intent {
        getTransactionsSpendingByDateRangeUseCase.invoke(
            startDate = startDate,
            endDate = endDate,
            limit = 4
        ).collectLatest {
            val state = when (it) {
                Result.Loading -> {
                    state.copy(
                        transactionSpendingState = TransactionSpendingState.Loading
                    )
                }

                is Result.Success -> {
                    val data = it.data
                    val checkData = it.data.filter { total ->
                        total.totalAllExpense == 0.0
                    }

                    if (data.size == checkData.size) {
                        state.copy(
                            transactionSpendingState = TransactionSpendingState.Empty
                        )
                    } else {
                        state.copy(
                            transactionSpendingState = TransactionSpendingState.Success(
                                data = data
                            )
                        )
                    }
                }

                is Result.Error -> {
                    state.copy(
                        transactionSpendingState = TransactionSpendingState.Error(
                            message = it.exception.message ?: "error"
                        )
                    )
                }
            }

            reduce {
                state
            }
        }
    }

    private fun getTransactionDailySpending() = intent {
        getRecentDailyTransactionSpendingUseCase.invoke().collectLatest {
            val state = when (it) {
                Result.Loading -> {
                    state.copy(
                        transactionDailySpendingState = TransactionDailySpendingState.Loading
                    )
                }

                is Result.Success -> {
                    if (it.data.daily.isEmpty()) {
                        state.copy(
                            transactionDailySpendingState = TransactionDailySpendingState.Empty
                        )
                    } else {
                        state.copy(
                            transactionDailySpendingState = TransactionDailySpendingState.Success(
                                data = it.data
                            )
                        )
                    }
                }

                is Result.Error -> {
                    state.copy(
                        transactionDailySpendingState = TransactionDailySpendingState.Error(
                            message = it.exception.message ?: "error"
                        )
                    )
                }
            }

            reduce {
                state
            }
        }
    }

    private fun getBudgetsExceeding() = intent {
        getBudgetsExceedingThresholdUseCase.invoke(
            threshold = 70.0
        ).collectLatest {
            val state = when (it) {
                Result.Loading -> {
                    state.copy(
                        budgetsExceedingState = BudgetsExceedingState.Loading
                    )
                }

                is Result.Success -> {
                    if (it.data.isEmpty()) {
                        state.copy(
                            budgetsExceedingState = BudgetsExceedingState.Empty
                        )
                    } else {
                        state.copy(
                            budgetsExceedingState = BudgetsExceedingState.Success(
                                data = it.data
                            )
                        )
                    }
                }

                is Result.Error -> {
                    state.copy(
                        budgetsExceedingState = BudgetsExceedingState.Error(
                            message = it.exception.message ?: "error"
                        )
                    )
                }
            }

            reduce {
                state
            }
        }
    }
}