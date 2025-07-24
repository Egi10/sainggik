package id.buaja.sainggik.feature.transactions.manage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.domain.model.transactions.TransactionsParam
import id.buaja.sainggik.domain.model.transactions.TransactionsUpdateParam
import id.buaja.sainggik.domain.usecase.categories.GetCategoriesUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsByIdUseCase
import id.buaja.sainggik.domain.usecase.transactions.SetTransactionsUseCase
import id.buaja.sainggik.domain.usecase.transactions.UpdateTransactionsUseCase
import id.buaja.sainggik.feature.transactions.manage.intent.ManageTransactionIntent
import id.buaja.sainggik.feature.transactions.manage.state.ManageTransactionSideEffect
import id.buaja.sainggik.feature.transactions.manage.state.ManageTransactionState
import kotlinx.coroutines.flow.collectLatest

class ManageTransactionViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val setTransactionsUseCase: SetTransactionsUseCase,
    private val getTransactionsByIdUseCase: GetTransactionsByIdUseCase,
    private val updateTransactionsUseCase: UpdateTransactionsUseCase
) : BaseViewModel<ManageTransactionState, ManageTransactionSideEffect>(initialState = ManageTransactionState()) {

    private var transactionsId by mutableStateOf<Long?>(null)

    var amount by mutableStateOf("")
        private set

    var note by mutableStateOf("")
        private set

    fun onIntent(intent: ManageTransactionIntent) {
        when (intent) {
            ManageTransactionIntent.Initial -> {
                getCategories()
            }

            is ManageTransactionIntent.InitialTransaction -> {
                transactionsId = intent.transactionsId
                getTransactionById(
                    id = intent.transactionsId
                )
            }

            ManageTransactionIntent.OnBackClick -> {
                sideEffect {
                    ManageTransactionSideEffect.NavigateBack
                }
            }

            is ManageTransactionIntent.OnAmountChanged -> {
                val rawText = intent.amount.filter {
                    it.isDigit()
                }
                if (rawText.length <= 12 && !(rawText.length == 1 && rawText == "0")) {
                    amount = rawText
                }
            }

            is ManageTransactionIntent.OnNoteChanged -> {
                note = intent.note
            }

            is ManageTransactionIntent.OnCategorySelected -> {
                updateState {
                    copy(selectedCategory = intent.category)
                }
            }

            is ManageTransactionIntent.OnDateSelected -> {
                updateState {
                    copy(date = intent.date)
                }
            }

            ManageTransactionIntent.OnCategoryClick -> {
                sideEffect { state ->
                    ManageTransactionSideEffect.ShowCategoryBottomSheet(
                        categories = state.availableCategories
                    )
                }
            }

            ManageTransactionIntent.OnDateClick -> {
                sideEffect {
                    ManageTransactionSideEffect.ShowDateBottomSheet
                }
            }

            ManageTransactionIntent.OnSubmitClick -> {
                transactionsId?.let { id ->
                    updateTransaction(transactionId = id)
                } ?: setTransaction()
            }
        }
    }

    private fun getCategories() = intent {
        getCategoriesUseCase()
            .collectLatest { result ->
                when (result) {
                    Result.Loading -> {
                        postSideEffect(
                            sideEffect = ManageTransactionSideEffect.ShowLoadingDialog
                        )
                    }

                    is Result.Success -> {
                        reduce {
                            state.copy(
                                availableCategories = result.data
                            )
                        }
                    }

                    is Result.Error -> {
                        postSideEffect(
                            sideEffect = ManageTransactionSideEffect.ShowToastMessage(
                                message = "Gagal memuat daftar kategori."
                            )
                        )
                    }
                }
            }
    }

    private fun setTransaction() = intent {
        val result = setTransactionsUseCase.invoke(
            param = TransactionsParam(
                amount = amount.toDouble(),
                categoryId = state.selectedCategory?.id ?: 0,
                note = note,
                transactionDate = state.date.toString()
            )
        )

        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                postSideEffect(
                    sideEffect = ManageTransactionSideEffect.NavigateBack
                )
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = ManageTransactionSideEffect.ShowToastMessage(
                        message = "Gagal menyimpan transaksi"
                    )
                )
            }
        }
    }

    private fun getTransactionById(id: Long) = intent {
        val result = getTransactionsByIdUseCase.invoke(
            id = id
        )

        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                val data = result.data

                amount = data.amount.toLong().toString()
                note = data.note
                reduce {
                    state.copy(
                        selectedCategory = state.availableCategories.find {
                            it.id == result.data.categoryId
                        },
                        date = DateTimeHelper.parseToLocalDate(
                            date = data.transactionDate,
                            format = DateTimeHelper.Formats.DATE
                        )
                    )
                }
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = ManageTransactionSideEffect.ShowToastMessage(
                        message = "Gagal mendapatkan transaksi"
                    )
                )
            }
        }
    }

    private fun updateTransaction(transactionId: Long) = intent {
        val result = updateTransactionsUseCase.invoke(
            param = TransactionsUpdateParam(
                id = transactionId,
                amount = amount.toDouble(),
                categoryId = state.selectedCategory?.id ?: 0,
                note = note,
                transactionDate = state.date.toString()
            )
        )

        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                postSideEffect(
                    sideEffect = ManageTransactionSideEffect.NavigateBack
                )
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = ManageTransactionSideEffect.ShowToastMessage(
                        message = "Gagal menyimpan transaksi"
                    )
                )
            }
        }
    }
}