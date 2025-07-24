package id.buaja.sainggik.domain.repository

import id.buaja.sainggik.domain.model.category.TypeCategory
import id.buaja.sainggik.domain.model.transactions.TransactionGroup
import id.buaja.sainggik.domain.model.transactions.Transaction
import id.buaja.sainggik.domain.model.transactions.TransactionDailySpending
import id.buaja.sainggik.domain.model.transactions.TransactionSpending
import id.buaja.sainggik.domain.model.transactions.TransactionSummary
import id.buaja.sainggik.domain.model.transactions.TransactionsParam
import id.buaja.sainggik.domain.model.transactions.TransactionsUpdateParam
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    suspend fun setTransactions(param: TransactionsParam)
    fun getAllTransactionByCategory(typeCategory: TypeCategory): Flow<List<TransactionGroup>>
    suspend fun deleteTransaction(id: Long)
    suspend fun getTransactionsById(id: Long): Transaction
    suspend fun updateTransaction(param: TransactionsUpdateParam)
    fun getSummaryTransactionByDateRange(
        startDate: String,
        endDate: String
    ): Flow<TransactionSummary>

    fun getTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<TransactionSpending>>

    fun getDailyTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<TransactionDailySpending>>
}