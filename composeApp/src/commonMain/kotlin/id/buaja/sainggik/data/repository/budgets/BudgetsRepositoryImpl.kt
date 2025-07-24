package id.buaja.sainggik.data.repository.budgets

import id.buaja.sainggik.data.repository.budgets.mapper.asBudgetDomainModel
import id.buaja.sainggik.data.repository.budgets.mapper.asBudgetExceedingDomainModel
import id.buaja.sainggik.data.repository.budgets.mapper.asBudgets
import id.buaja.sainggik.data.repository.budgets.mapper.asBudgetsGroupDomainModel
import id.buaja.sainggik.data.repository.budgets.mapper.asBudgetsSummaryGroupDomainModel
import id.buaja.sainggik.data.repository.budgets.mapper.asUpdateBudgets
import id.buaja.sainggik.data.source.budgets.BudgetsLocalDataSource
import id.buaja.sainggik.domain.model.budgets.BudgetGroup
import id.buaja.sainggik.domain.model.budgets.BudgetSummaryGroup
import id.buaja.sainggik.domain.model.budgets.BudgetUpdateParam
import id.buaja.sainggik.domain.model.budgets.Budget
import id.buaja.sainggik.domain.model.budgets.BudgetExceeding
import id.buaja.sainggik.domain.model.budgets.BudgetsParam
import id.buaja.sainggik.domain.repository.BudgetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BudgetsRepositoryImpl(
    private val budgetsLocalDataSource: BudgetsLocalDataSource
) : BudgetsRepository {
    override suspend fun setBudget(param: BudgetsParam): Long {
        return budgetsLocalDataSource.insertBudget(
            budgets = param.asBudgets()
        )
    }

    override fun getAllBudgetsWithCategory(): Flow<List<BudgetGroup>> {
        return budgetsLocalDataSource.getAllBudgetsWithCategory()
            .map {
                it.asBudgetsGroupDomainModel()
            }
    }

    override fun getBudgetSummaries(): Flow<List<BudgetSummaryGroup>> {
        return budgetsLocalDataSource.getBudgetSummaries()
            .map {
                it.asBudgetsSummaryGroupDomainModel()
            }
    }

    override suspend fun getBudgetById(id: Long): Budget {
        return budgetsLocalDataSource.getBudgetById(
            id = id
        ).asBudgetDomainModel()
    }

    override suspend fun deleteBudget(id: Long) {
        budgetsLocalDataSource.deleteBudget(
            id = id
        )
    }

    override suspend fun editBudget(param: BudgetUpdateParam) {
         budgetsLocalDataSource.editBudget(
             budgets = param.asUpdateBudgets()
         )
    }

    override fun getBudgetsExceedingThreshold(threshold: Double): Flow<List<BudgetExceeding>> {
        return budgetsLocalDataSource.getBudgetsExceedingThreshold(
            threshold = threshold
        ).map {
            it.asBudgetExceedingDomainModel()
        }
    }
}