package id.buaja.sainggik.domain.usecase.budgets

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.repository.BudgetsRepository

class DeleteBudgetUseCase(
    private val budgetsRepository: BudgetsRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return executeWithResult {
            budgetsRepository.deleteBudget(
                id = id
            )
        }
    }
}