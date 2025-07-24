package id.buaja.sainggik.domain.usecase.budgets

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.budgets.Budget
import id.buaja.sainggik.domain.repository.BudgetsRepository

class GetBudgetByIdUseCase(
    private val budgetsRepository: BudgetsRepository
) {
    suspend operator fun invoke(id: Long): Result<Budget> {
        return executeWithResult {
            budgetsRepository.getBudgetById(
                id = id
            )
        }
    }
}