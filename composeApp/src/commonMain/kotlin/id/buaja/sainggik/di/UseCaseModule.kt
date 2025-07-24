package id.buaja.sainggik.di

import id.buaja.sainggik.domain.usecase.budgets.DeleteBudgetUseCase
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetByIdUseCase
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetSummariesUseCase
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetsExceedingThresholdUseCase
import id.buaja.sainggik.domain.usecase.budgets.GetBudgetsWithCategoryUseCase
import id.buaja.sainggik.domain.usecase.budgets.SetBudgetsUseCase
import id.buaja.sainggik.domain.usecase.budgets.UpdateBudgetUseCase
import id.buaja.sainggik.domain.usecase.categories.DeleteCategoriesUseCase
import id.buaja.sainggik.domain.usecase.categories.GetCategoriesGroupUseCase
import id.buaja.sainggik.domain.usecase.categories.GetCategoriesUseCase
import id.buaja.sainggik.domain.usecase.categories.SetCategoriesUseCase
import id.buaja.sainggik.domain.usecase.categories.UpdateCategoriesUseCase
import id.buaja.sainggik.domain.usecase.transactions.DeleteTransactionsByIdUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetRecentDailyTransactionSpendingUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsByCategoryTypeUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsByIdUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsSpendingByDateRangeUseCase
import id.buaja.sainggik.domain.usecase.transactions.GetTransactionsSummaryByDateRangeUseCase
import id.buaja.sainggik.domain.usecase.transactions.SetTransactionsUseCase
import id.buaja.sainggik.domain.usecase.transactions.UpdateTransactionsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetBudgetsWithCategoryUseCase)
    factoryOf(::SetBudgetsUseCase)
    factoryOf(::GetBudgetSummariesUseCase)
    factoryOf(::DeleteBudgetUseCase)
    factoryOf(::UpdateBudgetUseCase)
    factoryOf(::GetBudgetByIdUseCase)
    factoryOf(::GetBudgetsExceedingThresholdUseCase)

    factoryOf(::GetCategoriesUseCase)
    factoryOf(::GetCategoriesGroupUseCase)
    factoryOf(::SetCategoriesUseCase)
    factoryOf(::DeleteCategoriesUseCase)
    factoryOf(::UpdateCategoriesUseCase)

    factoryOf(::GetTransactionsByCategoryTypeUseCase)
    factoryOf(::SetTransactionsUseCase)
    factoryOf(::DeleteTransactionsByIdUseCase)
    factoryOf(::GetTransactionsByIdUseCase)
    factoryOf(::UpdateTransactionsUseCase)
    factoryOf(::GetTransactionsSummaryByDateRangeUseCase)
    factoryOf(::GetTransactionsSpendingByDateRangeUseCase)
    factoryOf(::GetRecentDailyTransactionSpendingUseCase)
}