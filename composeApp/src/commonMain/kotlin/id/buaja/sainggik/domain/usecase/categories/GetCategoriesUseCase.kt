package id.buaja.sainggik.domain.usecase.categories

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.category.Category
import id.buaja.sainggik.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val categoriesRepository: CategoriesRepository
) {
    operator fun invoke(): Flow<Result<List<Category>>> {
        return executeWithResultStreamingFlow {
            categoriesRepository.getAllCategory()
        }
    }
}