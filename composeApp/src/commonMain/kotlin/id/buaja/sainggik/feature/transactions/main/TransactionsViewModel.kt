package id.buaja.sainggik.feature.transactions.main

import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.domain.model.category.TypeCategory
import id.buaja.sainggik.domain.usecase.transactions.DeleteTransactionsByIdUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsByCategoryTypeUseCase
import id.buaja.sainggik.feature.transactions.main.intent.TransactionsIntent
import id.buaja.sainggik.feature.transactions.main.state.TransactionsSideEffect
import id.buaja.sainggik.feature.transactions.main.state.TransactionsState
import kotlinx.coroutines.flow.collectLatest

class TransactionsViewModel(
    private val getTransactionsByCategoryTypeUseCase: GetTransactionsByCategoryTypeUseCase,
    private val deleteTransactionsByIdUseCase: DeleteTransactionsByIdUseCase
) : BaseViewModel<TransactionsState, TransactionsSideEffect>(initialState = TransactionsState()) {

    fun onIntent(intent: TransactionsIntent) {
        when (intent) {
            is TransactionsIntent.ChangeTab -> {
                updateState {
                    copy(
                        selectedTab = intent.index
                    )
                }

                val type = when (intent.index) {
                    0 -> TypeCategory.EXPENSE
                    1 -> TypeCategory.INCOME
                    else -> TypeCategory.EXPENSE
                }

                getTransactions(
                    typeCategory = type
                )
            }

            TransactionsIntent.FabClicked -> {
                sideEffect {
                    TransactionsSideEffect.NavigateToAddTransaction
                }
            }

            is TransactionsIntent.OnDeleteTransactionsClick -> {
                deleteTransaction(
                    id = intent.id
                )
            }

            is TransactionsIntent.OnEditTransactionsClick -> {
                sideEffect {
                    TransactionsSideEffect.NavigateToEditTransaction(
                        id = intent.id
                    )
                }
            }
        }
    }

    private fun getTransactions(typeCategory: TypeCategory) = intent {
        getTransactionsByCategoryTypeUseCase.invoke(
            typeCategory = typeCategory
        ).collectLatest { result ->
            val newState = when (result) {
                Result.Loading -> state.transactionsListState.copy(
                    isLoading = true,
                    errorMessage = null
                )

                is Result.Success -> state.transactionsListState.copy(
                    isLoading = false,
                    data = result.data,
                    errorMessage = null
                )

                is Result.Error -> state.transactionsListState.copy(
                    isLoading = false,
                    errorMessage = result.exception.message
                )
            }

            reduce {
                state.copy(
                    transactionsListState = newState
                )
            }
        }
    }

    private fun deleteTransaction(id: Long) = intent {
        val result = deleteTransactionsByIdUseCase.invoke(
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