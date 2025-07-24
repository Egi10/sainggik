package id.buaja.sainggik.feature.home.state

import id.buaja.sainggik.domain.model.budgets.BudgetExceeding
import id.buaja.sainggik.domain.model.transactions.TransactionSpending
import id.buaja.sainggik.domain.model.transactions.TransactionSummary
import id.buaja.sainggik.domain.model.transactions.TransactionsRecentDaily

data class HomeState(
    val filter: List<HomeFilterState> = emptyList(),
    val filterSelected: HomeFilterState? = null,
    val transactionSummaryState: TransactionSummaryState = TransactionSummaryState(),
    val transactionSpendingState: TransactionSpendingState = TransactionSpendingState.Loading,
    val transactionDailySpendingState: TransactionDailySpendingState = TransactionDailySpendingState.Loading,
    val budgetsExceedingState: BudgetsExceedingState = BudgetsExceedingState.Loading
)

data class TransactionSummaryState(
    val isLoading: Boolean = false,
    val data: TransactionSummary? = null
)

sealed interface TransactionSpendingState {
    data object Loading : TransactionSpendingState
    data class Success(val data: List<TransactionSpending>) : TransactionSpendingState
    data object Empty : TransactionSpendingState
    data class Error(val message: String) : TransactionSpendingState
}

sealed interface TransactionDailySpendingState {
    data object Loading : TransactionDailySpendingState
    data class Success(val data: TransactionsRecentDaily) : TransactionDailySpendingState
    data object Empty : TransactionDailySpendingState
    data class Error(val message: String) : TransactionDailySpendingState
}

sealed interface BudgetsExceedingState {
    data object Loading : BudgetsExceedingState
    data class Success(val data: List<BudgetExceeding>) : BudgetsExceedingState
    data object Empty : BudgetsExceedingState
    data class Error(val message: String) : BudgetsExceedingState
}

data class HomeFilterState(
    val key: HomeFilterEnum,
    val value: String
)

enum class HomeFilterEnum {
    THIS_WEEK,
    THIS_MONTH,
    CUSTOM
}