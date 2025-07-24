package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.transactions.TransactionSpending
import id.buaja.sainggik.domain.repository.TransactionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTransactionsSpendingByDateRangeUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(
        startDate: String,
        endDate: String,
        limit: Int? = null
    ): Flow<Result<List<TransactionSpending>>> {
        return executeWithResultStreamingFlow {
            transactionsRepository.getTransactionSpendingByDateRange(
                startDate = startDate,
                endDate = endDate
            ).map {
                return@map if (limit != null) {
                    it.take(limit)
                } else {
                    it
                }
            }
        }
    }
}