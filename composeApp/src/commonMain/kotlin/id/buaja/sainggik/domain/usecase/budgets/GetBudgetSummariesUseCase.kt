package id.buaja.sainggik.domain.usecase.budgets

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.budgets.BudgetSummaryGroup
import id.buaja.sainggik.domain.repository.BudgetsRepository
import kotlinx.coroutines.flow.Flow

class GetBudgetSummariesUseCase(
    private val budgetsRepository: BudgetsRepository
) {
    operator fun invoke(): Flow<Result<List<BudgetSummaryGroup>>> {
        return executeWithResultStreamingFlow {
            budgetsRepository.getBudgetSummaries()
        }
    }
}