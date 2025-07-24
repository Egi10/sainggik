package id.buaja.sainggik.feature.transactions.manage.state

import id.buaja.sainggik.domain.model.category.Category

sealed interface ManageTransactionSideEffect {
    data object NavigateBack : ManageTransactionSideEffect
    data object ShowLoadingDialog : ManageTransactionSideEffect
    data class ShowToastMessage(val message: String) : ManageTransactionSideEffect
    data class ShowCategoryBottomSheet(
        val categories: List<Category>
    ) : ManageTransactionSideEffect

    data object ShowDateBottomSheet : ManageTransactionSideEffect
}