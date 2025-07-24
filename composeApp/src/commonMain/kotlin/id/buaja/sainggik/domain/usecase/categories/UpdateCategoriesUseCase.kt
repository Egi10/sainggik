package id.buaja.sainggik.domain.usecase.categories

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.category.CategoryUpdateParam
import id.buaja.sainggik.domain.repository.CategoriesRepository

class UpdateCategoriesUseCase(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(param: CategoryUpdateParam): Result<Unit> {
        return executeWithResult {
            categoriesRepository.updateCategory(
                param = param
            )
        }
    }
}