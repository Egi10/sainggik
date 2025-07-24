package id.buaja.sainggik.domain.usecase.budgets

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.budgets.BudgetUpdateParam
import id.buaja.sainggik.domain.repository.BudgetsRepository

class UpdateBudgetUseCase(
    private val budgetsRepository: BudgetsRepository
) {
    suspend operator fun invoke(param: BudgetUpdateParam): Result<Unit> {
        return executeWithResult {
            budgetsRepository.editBudget(
                param = param
            )
        }
    }
}