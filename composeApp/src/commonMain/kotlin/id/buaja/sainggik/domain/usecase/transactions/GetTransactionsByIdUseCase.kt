package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.transactions.Transaction
import id.buaja.sainggik.domain.repository.TransactionsRepository

class GetTransactionsByIdUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(id: Long): Result<Transaction> {
        return executeWithResult {
            transactionsRepository.getTransactionsById(
                id = id
            )
        }
    }
}