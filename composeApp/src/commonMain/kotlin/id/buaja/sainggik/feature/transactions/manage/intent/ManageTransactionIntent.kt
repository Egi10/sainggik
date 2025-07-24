package id.buaja.sainggik.feature.transactions.manage.intent

import id.buaja.sainggik.domain.model.category.Category
import kotlinx.datetime.LocalDate

typealias OnManageTransactionIntent = (ManageTransactionIntent) -> Unit

sealed interface ManageTransactionIntent {
    data object Initial : ManageTransactionIntent
    data class InitialTransaction(val transactionsId: Long) : ManageTransactionIntent
    data object OnBackClick : ManageTransactionIntent

    data class OnAmountChanged(val amount: String) : ManageTransactionIntent
    data class OnNoteChanged(val note: String) : ManageTransactionIntent
    data class OnCategorySelected(val category: Category) : ManageTransactionIntent
    data class OnDateSelected(val date: LocalDate) : ManageTransactionIntent

    data object OnCategoryClick : ManageTransactionIntent
    data object OnDateClick : ManageTransactionIntent

    data object OnSubmitClick : ManageTransactionIntent
}
