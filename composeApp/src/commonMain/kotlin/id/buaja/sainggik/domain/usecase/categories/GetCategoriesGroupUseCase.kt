package id.buaja.sainggik.domain.usecase.categories

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.category.CategoryGroup
import id.buaja.sainggik.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesGroupUseCase(
    private val categoriesRepository: CategoriesRepository
) {
    operator fun invoke(): Flow<Result<List<CategoryGroup>>> {
        return executeWithResultStreamingFlow {
            categoriesRepository.getCategoryGroup()
        }
    }
}