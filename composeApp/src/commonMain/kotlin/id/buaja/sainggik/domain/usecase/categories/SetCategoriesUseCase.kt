package id.buaja.sainggik.domain.usecase.categories

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.category.CategoryParam
import id.buaja.sainggik.domain.repository.CategoriesRepository

class SetCategoriesUseCase(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(param: CategoryParam): Result<Unit> {
        return executeWithResult {
            categoriesRepository.setCategory(
                param = param
            )
        }
    }
}