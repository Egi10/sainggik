package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.transactions.TransactionsParam
import id.buaja.sainggik.domain.repository.TransactionsRepository

class SetTransactionsUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(param: TransactionsParam): Result<Unit> {
        return executeWithResult {
            transactionsRepository.setTransactions(
                param = param
            )
        }
    }
}