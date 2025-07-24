package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResult
import id.buaja.sainggik.domain.model.transactions.TransactionsUpdateParam
import id.buaja.sainggik.domain.repository.TransactionsRepository

class UpdateTransactionsUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(param: TransactionsUpdateParam): Result<Unit> {
        return executeWithResult {
            transactionsRepository.updateTransaction(
                param = param
            )
        }
    }
}