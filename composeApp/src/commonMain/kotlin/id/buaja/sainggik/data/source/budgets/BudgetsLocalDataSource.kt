package id.buaja.sainggik.data.source.budgets

import id.buaja.sainggik.data.Budgets
import id.buaja.sainggik.data.GetBudgetsExceedingThreshold
import id.buaja.sainggik.data.SelectAllBudgetsWithCategory
import id.buaja.sainggik.data.SelectBudgetSummary
import kotlinx.coroutines.flow.Flow

interface BudgetsLocalDataSource {
    suspend fun insertBudget(budgets: Budgets): Long
    fun getAllBudgetsWithCategory(): Flow<List<SelectAllBudgetsWithCategory>>
    fun getBudgetSummaries(): Flow<List<SelectBudgetSummary>>
    suspend fun getBudgetById(id: Long): Budgets
    suspend fun deleteBudget(id: Long): Long
    suspend fun editBudget(budgets: Budgets): Long
    fun getBudgetsExceedingThreshold(threshold: Double): Flow<List<GetBudgetsExceedingThreshold>>
}