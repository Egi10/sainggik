package id.buaja.sainggik.domain.usecase.budgets

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.budgets.BudgetsParam
import id.buaja.sainggik.domain.repository.BudgetsRepository

class SetBudgetsUseCase(
    private val budgetsRepository: BudgetsRepository
) {
    suspend operator fun invoke(
        param: BudgetsParam
    ): Result<Long> {
        return executeWithResult {
            budgetsRepository.setBudget(
                param = param
            )
        }
    }
}