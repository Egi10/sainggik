package id.buaja.sainggik.domain.usecase.categories

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.repository.CategoriesRepository

class DeleteCategoriesUseCase(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return executeWithResult {
            categoriesRepository.deleteCategory(
                id = id
            )
        }
    }
}