package id.buaja.sainggik.data.repository.transactions

import id.buaja.sainggik.data.repository.transactions.mapper.asCategoriesTypeEntity
import id.buaja.sainggik.data.repository.transactions.mapper.asSummaryTransactionDomainModel
import id.buaja.sainggik.data.repository.transactions.mapper.asTransactionDailySpendingDomainModel
import id.buaja.sainggik.data.repository.transactions.mapper.asTransactionDomainModel
import id.buaja.sainggik.data.repository.transactions.mapper.asTransactionGroupDomainModel
import id.buaja.sainggik.data.repository.transactions.mapper.asTransactionSpendingDomainModel
import id.buaja.sainggik.data.repository.transactions.mapper.asTransactions
import id.buaja.sainggik.data.repository.transactions.mapper.asTransactionsUpdate
import id.buaja.sainggik.data.source.transactions.TransactionsLocalDataSource
import id.buaja.sainggik.domain.model.category.TypeCategory
import id.buaja.sainggik.domain.model.transactions.TransactionGroup
import id.buaja.sainggik.domain.model.transactions.Transaction
import id.buaja.sainggik.domain.model.transactions.TransactionDailySpending
import id.buaja.sainggik.domain.model.transactions.TransactionSpending
import id.buaja.sainggik.domain.model.transactions.TransactionSummary
import id.buaja.sainggik.domain.model.transactions.TransactionsParam
import id.buaja.sainggik.domain.model.transactions.TransactionsUpdateParam
import id.buaja.sainggik.domain.repository.TransactionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionsRepositoryImpl(
    private val transactionsLocalDataSource: TransactionsLocalDataSource
) : TransactionsRepository {
    override suspend fun setTransactions(param: TransactionsParam) {
        transactionsLocalDataSource.insertTransactions(
            transactions = param.asTransactions()
        )
    }

    override fun getAllTransactionByCategory(typeCategory: TypeCategory): Flow<List<TransactionGroup>> {
        return transactionsLocalDataSource.getAllTransactions(
            type = typeCategory.asCategoriesTypeEntity()
        ).map {
            it.asTransactionGroupDomainModel()
        }
    }

    override suspend fun deleteTransaction(id: Long) {
        transactionsLocalDataSource.deleteTransactions(
            id = id
        )
    }

    override suspend fun getTransactionsById(id: Long): Transaction {
        return transactionsLocalDataSource.getTransactionsById(
            id = id
        ).asTransactionDomainModel()
    }

    override suspend fun updateTransaction(param: TransactionsUpdateParam) {
        transactionsLocalDataSource.updateTransactions(
            transactions = param.asTransactionsUpdate()
        )
    }

    override fun getSummaryTransactionByDateRange(
        startDate: String,
        endDate: String
    ): Flow<TransactionSummary> {
        return transactionsLocalDataSource.getSummaryTransactionByDateRange(
            startDate = startDate,
            endDate = endDate
        ).map {
            it.asSummaryTransactionDomainModel()
        }
    }

    override fun getTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<TransactionSpending>> {
        return transactionsLocalDataSource.getTransactionSpendingByDateRange(
            startDate = startDate,
            endDate = endDate
        ).map {
            it.asTransactionSpendingDomainModel()
        }
    }

    override fun getDailyTransactionSpendingByDateRange(
        startDate: String,
        endDate: String
    ): Flow<List<TransactionDailySpending>> {
        return transactionsLocalDataSource.getDailyTransactionSpendingByDateRange(
            startDate = startDate,
            endDate = endDate
        ).map {
            it.asTransactionDailySpendingDomainModel()
        }
    }
}