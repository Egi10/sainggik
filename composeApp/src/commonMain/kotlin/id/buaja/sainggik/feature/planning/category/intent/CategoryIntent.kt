package id.buaja.sainggik.feature.planning.category.intent

import id.buaja.sainggik.domain.model.category.Category
import id.buaja.sainggik.feature.planning.category.state.ManageCategoryState

typealias OnCategoryIntent = (CategoryIntent) -> Unit

sealed interface CategoryIntent {
    data object Initial : CategoryIntent
    data class UpdateCategoryName(val name: String) : CategoryIntent
    data class UpdateCategoryType(val type: ManageCategoryState.CategoryType) : CategoryIntent
    data object SubmitCategory : CategoryIntent
    data class DeleteCategory(val id: Long) : CategoryIntent
    data class EditCategory(val category: Category) : CategoryIntent
}