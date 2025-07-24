package id.buaja.sainggik.data.source.transactions

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import id.buaja.sainggik.core.database.entity.CategoriesTypeEntity
import id.buaja.sainggik.core.utils.DispatcherProvider
import id.buaja.sainggik.data.GetDailyTransactionSpendingByDateRange
import id.buaja.sainggik.data.GetSummaryTransactionsByDateRange
import id.buaja.sainggik.data.GetTransactionSpendingByDateRange
import id.buaja.sainggik.data.SelectAllTransactionByCategoryType
import id.buaja.sainggik.data.SelectAllTransactionById
import id.buaja.sainggik.data.Transactions
import id.buaja.sainggik.data.TransactionsQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TransactionsLocalDataSourceImpl(
    private val transactionsQueries: TransactionsQueries,
    private val dispatcherProvider: DispatcherProvider
) : TransactionsLocalDataSource {
    override suspend fun insertTransactions(transactions: Transactions): Long = withContext(dispatcherProvider.io) {
        return@withContext transactionsQueries.insertTransaction(
            id = null,
            amount = transactions.amount,
            category_id = transactions.category_id,
            note = transactions.note,
            transaction_date = transactions.transaction_date,
            created_at = transactions.created_at,
            updated_at = transactions.updated_at
        )
    }

    override fun getAllTransactions(type: CategoriesTypeEntity): Flow<List<SelectAllTransactionByCategoryType>> {
        return transactionsQueries.selectAllTransactionByCategoryType(
            type = type
        ).asFlow().mapToList(dispatcherProvider.io)
    }

    override suspend fun deleteTransactions(id: Long): Long = withContext(dispatcherProvider.io) {
        return@withContext transactionsQueries.deleteTransaction(
            id = id
        )
    }

    override suspend fun getTransactionsById(id: Long): SelectAllTransactionById = withContext(dispatcherProvider.io) {
        return@withContext transactionsQueries.selectAllTransactionById(
            id = id
        ).executeAsOne()
    }

    override suspend fun updateTransactions(transactions: Transactions): Long = withContext(dispatcherProvider.io) {
        return@withContext transactionsQueries.updateTransaction(
            amount = transactions.amount,
            category_id = transactions.category_id,
            note = transactions.note,
            updated_at = transactions.updated_at,
            transaction_date = transactions.transaction_date,
            id = transactions.id
        )
    }

    override fun getSummaryTransactionByDateRange(
        startDate: String,
        endDate: String
    ): Flow<GetSummaryTransactionsByDateRange> {
        return transactionsQueries.getSummaryTransactionsByDateRange(
            startDate = startDate,
            endDate = endDate
        ).asFlow().mapToOne(dispatcherProvider.io)
    }

    override fun getTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<GetTransactionSpendingByDateRange>> {
        return transactionsQueries.getTransactionSpendingByDateRange(
            startDate = startDate,
            endDate = endDate
        ).asFlow().mapToList(dispatcherProvider.io)
    }

    override fun getDailyTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<GetDailyTransactionSpendingByDateRange>> {
        return transactionsQueries.getDailyTransactionSpendingByDateRange(
            startDate = startDate,
            endDate = endDate
        ).asFlow().mapToList(dispatcherProvider.io)
    }
}