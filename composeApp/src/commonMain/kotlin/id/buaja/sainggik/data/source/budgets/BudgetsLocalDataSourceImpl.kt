package id.buaja.sainggik.data.source.budgets

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import id.buaja.sainggik.core.utils.DispatcherProvider
import id.buaja.sainggik.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BudgetsLocalDataSourceImpl(
    private val budgetsQueries: BudgetsQueries,
    private val dispatcherProvider: DispatcherProvider
) : BudgetsLocalDataSource {
    override suspend fun insertBudget(budgets: Budgets): Long = withContext(dispatcherProvider.io) {
        return@withContext budgetsQueries.insertBudget(
            id = null,
            category_id = budgets.category_id,
            start_date = budgets.start_date,
            end_date = budgets.end_date,
            amount_limit = budgets.amount_limit,
            created_at = budgets.created_at,
            updated_at = budgets.updated_at
        )
    }

    override fun getAllBudgetsWithCategory(): Flow<List<SelectAllBudgetsWithCategory>> {
        return budgetsQueries.selectAllBudgetsWithCategory()
            .asFlow()
            .mapToList(context = dispatcherProvider.io)
    }

    override fun getBudgetSummaries(): Flow<List<SelectBudgetSummary>> {
        return budgetsQueries.selectBudgetSummary()
            .asFlow()
            .mapToList(context = dispatcherProvider.io)
    }

    override suspend fun getBudgetById(id: Long): Budgets = withContext(dispatcherProvider.io) {
        return@withContext budgetsQueries.selectBudgetById(
            budgetId = id
        ).executeAsOne()
    }

    override suspend fun deleteBudget(id: Long): Long = withContext(dispatcherProvider.io) {
        return@withContext budgetsQueries.deleteBudget(
            id = id
        )
    }

    override suspend fun editBudget(budgets: Budgets): Long = withContext(dispatcherProvider.io) {
        return@withContext budgetsQueries.updateBudget(
            start_date = budgets.start_date,
            end_date = budgets.end_date,
            amount_limit = budgets.amount_limit,
            updated_at = budgets.updated_at,
            id = budgets.id
        )
    }

    override fun getBudgetsExceedingThreshold(threshold: Double): Flow<List<GetBudgetsExceedingThreshold>> {
        return budgetsQueries.getBudgetsExceedingThreshold(
            thresholdPercent = threshold
        ).asFlow().mapToList(dispatcherProvider.io)
    }
}