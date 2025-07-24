package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.repository.TransactionsRepository

class DeleteTransactionsByIdUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return executeWithResult {
            transactionsRepository.deleteTransaction(
                id = id
            )
        }
    }
}