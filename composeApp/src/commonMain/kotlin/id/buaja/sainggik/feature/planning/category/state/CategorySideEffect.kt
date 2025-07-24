package id.buaja.sainggik.feature.planning.category.state

sealed interface CategorySideEffect {
    data class ShowAddCategoryToast(val message: String) : CategorySideEffect
    data object ShowUpdateCategoryBottomSheet : CategorySideEffect
}