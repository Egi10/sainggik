package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.category.TypeCategory
import id.buaja.sainggik.domain.model.transactions.TransactionGroup
import id.buaja.sainggik.domain.repository.TransactionsRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionsByCategoryTypeUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(typeCategory: TypeCategory): Flow<Result<List<TransactionGroup>>> {
        return executeWithResultStreamingFlow {
            transactionsRepository.getAllTransactionByCategory(
                typeCategory = typeCategory
            )
        }
    }
}