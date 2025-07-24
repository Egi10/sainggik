package id.buaja.sainggik.domain.usecase.budgets

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.budgets.BudgetGroup
import id.buaja.sainggik.domain.repository.BudgetsRepository
import kotlinx.coroutines.flow.Flow

class GetBudgetsWithCategoryUseCase(
    private val budgetsRepository: BudgetsRepository
) {
    operator fun invoke(): Flow<Result<List<BudgetGroup>>> {
        return executeWithResultStreamingFlow {
            budgetsRepository.getAllBudgetsWithCategory()
        }
    }
}