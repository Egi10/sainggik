package id.buaja.sainggik.data.source.transactions

import id.buaja.sainggik.core.database.entity.CategoriesTypeEntity
import id.buaja.sainggik.data.GetDailyTransactionSpendingByDateRange
import id.buaja.sainggik.data.GetSummaryTransactionsByDateRange
import id.buaja.sainggik.data.GetTransactionSpendingByDateRange
import id.buaja.sainggik.data.SelectAllTransactionByCategoryType
import id.buaja.sainggik.data.SelectAllTransactionById
import id.buaja.sainggik.data.Transactions
import kotlinx.coroutines.flow.Flow

interface TransactionsLocalDataSource {
    suspend fun insertTransactions(transactions: Transactions): Long
    fun getAllTransactions(type: CategoriesTypeEntity): Flow<List<SelectAllTransactionByCategoryType>>
    suspend fun deleteTransactions(id: Long): Long
    suspend fun getTransactionsById(id: Long): SelectAllTransactionById
    suspend fun updateTransactions(transactions: Transactions): Long
    fun getSummaryTransactionByDateRange(
        startDate: String,
        endDate: String
    ): Flow<GetSummaryTransactionsByDateRange>

    fun getTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<GetTransactionSpendingByDateRange>>

    fun getDailyTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<GetDailyTransactionSpendingByDateRange>>
}