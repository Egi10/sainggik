package id.buaja.sainggik.domain.usecase.budgets

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.budgets.BudgetExceeding
import id.buaja.sainggik.domain.repository.BudgetsRepository
import kotlinx.coroutines.flow.Flow

class GetBudgetsExceedingThresholdUseCase(
    private val budgetsRepository: BudgetsRepository
) {
    operator fun invoke(threshold: Double): Flow<Result<List<BudgetExceeding>>> {
        return executeWithResultStreamingFlow {
            budgetsRepository.getBudgetsExceedingThreshold(
                threshold = threshold
            )
        }
    }
}