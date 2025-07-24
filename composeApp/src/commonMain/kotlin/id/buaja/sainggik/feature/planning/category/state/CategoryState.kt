package id.buaja.sainggik.feature.planning.category.state

import id.buaja.sainggik.domain.model.category.CategoryGroup

data class CategoryState(
    val listCategoryState: ListCategoryState = ListCategoryState(),
    val manageCategoryState: ManageCategoryState = ManageCategoryState()
)

data class ListCategoryState(
    val isLoading: Boolean = false,
    val data: List<CategoryGroup> = emptyList(),
    val errorMessage: String? = null
)

data class ManageCategoryState(
    val categoryType: CategoryType? = null,
    val isUpdate: Boolean = false
) {
    enum class CategoryType {
        INCOME,
        EXPENSE
    }
}