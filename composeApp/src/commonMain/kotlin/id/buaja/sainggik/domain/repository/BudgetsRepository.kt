package id.buaja.sainggik.domain.repository

import id.buaja.sainggik.domain.model.budgets.BudgetGroup
import id.buaja.sainggik.domain.model.budgets.BudgetSummaryGroup
import id.buaja.sainggik.domain.model.budgets.BudgetUpdateParam
import id.buaja.sainggik.domain.model.budgets.Budget
import id.buaja.sainggik.domain.model.budgets.BudgetExceeding
import id.buaja.sainggik.domain.model.budgets.BudgetsParam
import kotlinx.coroutines.flow.Flow

interface BudgetsRepository {
    suspend fun setBudget(param: BudgetsParam): Long
    fun getAllBudgetsWithCategory(): Flow<List<BudgetGroup>>
    fun getBudgetSummaries(): Flow<List<BudgetSummaryGroup>>
    suspend fun getBudgetById(id: Long): Budget
    suspend fun deleteBudget(id: Long)
    suspend fun editBudget(param: BudgetUpdateParam)
    fun getBudgetsExceedingThreshold(threshold: Double): Flow<List<BudgetExceeding>>
}