package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.transactions.TransactionSummary
import id.buaja.sainggik.domain.repository.TransactionsRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionsSummaryByDateRangeUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(
        startDate: String,
        endDate: String
    ): Flow<Result<TransactionSummary>> {
        return executeWithResultStreamingFlow {
            transactionsRepository.getSummaryTransactionByDateRange(
                startDate = startDate,
                endDate = endDate
            )
        }
    }
}