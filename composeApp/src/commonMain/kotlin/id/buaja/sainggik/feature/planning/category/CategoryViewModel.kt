package id.buaja.sainggik.feature.planning.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.domain.model.category.CategoryParam
import id.buaja.sainggik.domain.model.category.CategoryUpdateParam
import id.buaja.sainggik.domain.model.category.TypeCategory
import id.buaja.sainggik.domain.usecase.categories.DeleteCategoriesUseCase
import id.buaja.sainggik.domain.usecase.categories.GetCategoriesGroupUseCase
import id.buaja.sainggik.domain.usecase.categories.SetCategoriesUseCase
import id.buaja.sainggik.domain.usecase.categories.UpdateCategoriesUseCase
import id.buaja.sainggik.feature.planning.category.intent.CategoryIntent
import id.buaja.sainggik.feature.planning.category.state.ManageCategoryState
import id.buaja.sainggik.feature.planning.category.state.CategorySideEffect
import id.buaja.sainggik.feature.planning.category.state.CategoryState
import kotlinx.coroutines.flow.collectLatest

class CategoryViewModel(
    private val getCategoriesGroupUseCase: GetCategoriesGroupUseCase,
    private val setCategoriesUseCase: SetCategoriesUseCase,
    private val deleteCategoriesUseCase: DeleteCategoriesUseCase,
    private val updateCategoriesUseCase: UpdateCategoriesUseCase
) : BaseViewModel<CategoryState, CategorySideEffect>(initialState = CategoryState()) {

    private var id by mutableStateOf<Long?>(null)
    var name by mutableStateOf("")
        private set

    fun onIntent(intent: CategoryIntent) {
        when (intent) {
            CategoryIntent.Initial -> {
                getCategories()
            }

            is CategoryIntent.UpdateCategoryName -> {
                name = intent.name
            }

            is CategoryIntent.UpdateCategoryType -> {
                updateState {
                    copy(
                        manageCategoryState = manageCategoryState.copy(
                            categoryType = intent.type
                        )
                    )
                }
            }

            CategoryIntent.SubmitCategory -> {
                id?.let {
                    updateCategories(
                        id = it
                    )
                } ?: setCategories()
            }

            is CategoryIntent.DeleteCategory -> {
                deleteCategories(
                    id = intent.id
                )
            }

            is CategoryIntent.EditCategory -> {
                id = intent.category.id
                name = intent.category.name
                updateState {
                    copy(
                        manageCategoryState = manageCategoryState.copy(
                            categoryType = when (intent.category.type) {
                                TypeCategory.INCOME -> ManageCategoryState.CategoryType.INCOME
                                TypeCategory.EXPENSE -> ManageCategoryState.CategoryType.EXPENSE
                            },
                            isUpdate = true
                        )
                    )
                }

                sideEffect {
                    CategorySideEffect.ShowUpdateCategoryBottomSheet
                }
            }
        }
    }

    private fun setCategories() = intent {
        val result = setCategoriesUseCase.invoke(
            param = CategoryParam(
                name = name,
                typeCategory = when (state.manageCategoryState.categoryType) {
                    ManageCategoryState.CategoryType.INCOME -> TypeCategory.INCOME
                    ManageCategoryState.CategoryType.EXPENSE -> TypeCategory.EXPENSE
                    else -> TypeCategory.INCOME
                }
            )
        )
        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                postSideEffect(
                    sideEffect = CategorySideEffect.ShowAddCategoryToast(
                        message = "Suksess"
                    )
                )
                reduce {
                    state.copy(
                        manageCategoryState = ManageCategoryState()
                    )
                }
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = CategorySideEffect.ShowAddCategoryToast(
                        message = "Failed"
                    )
                )
            }
        }
    }

    private fun getCategories() = intent {
        getCategoriesGroupUseCase()
            .collectLatest { result ->
                val newCategoryState = when (result) {
                    Result.Loading -> state.listCategoryState.copy(
                        isLoading = true,
                        errorMessage = null
                    )

                    is Result.Success -> state.listCategoryState.copy(
                        isLoading = false,
                        data = result.data,
                        errorMessage = null
                    )

                    is Result.Error -> state.listCategoryState.copy(
                        isLoading = false,
                        errorMessage = result.exception.message
                    )
                }

                reduce {
                    state.copy(listCategoryState = newCategoryState)
                }
            }
    }

    private fun deleteCategories(id: Long) = intent {
        val result = deleteCategoriesUseCase.invoke(
            id = id
        )
        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                postSideEffect(
                    sideEffect = CategorySideEffect.ShowAddCategoryToast(
                        message = "Suksess Delete Categori"
                    )
                )
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = CategorySideEffect.ShowAddCategoryToast(
                        message = "Gagal Delete Categori"
                    )
                )
            }
        }
    }

    private fun updateCategories(id: Long) = intent {
        val result = updateCategoriesUseCase.invoke(
            param = CategoryUpdateParam(
                id = id,
                name = name,
                typeCategory = when (state.manageCategoryState.categoryType) {
                    ManageCategoryState.CategoryType.INCOME -> TypeCategory.INCOME
                    ManageCategoryState.CategoryType.EXPENSE -> TypeCategory.EXPENSE
                    else -> TypeCategory.INCOME
                }
            )
        )
        when (result) {
            Result.Loading -> {}
            is Result.Success -> {
                postSideEffect(
                    sideEffect = CategorySideEffect.ShowAddCategoryToast(
                        message = "Suksess Update Categori"
                    )
                )
            }

            is Result.Error -> {
                postSideEffect(
                    sideEffect = CategorySideEffect.ShowAddCategoryToast(
                        message = "Gagal Update Categori"
                    )
                )
            }
        }
    }
}