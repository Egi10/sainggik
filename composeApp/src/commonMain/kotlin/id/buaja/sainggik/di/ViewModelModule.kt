package id.buaja.sainggik.di

import id.buaja.sainggik.feature.home.HomeViewModel
import id.buaja.sainggik.feature.planning.budget.list.ListBudgetViewModel
import id.buaja.sainggik.feature.planning.budget.manage.ManageBudgetViewModel
import id.buaja.sainggik.feature.planning.category.CategoryViewModel
import id.buaja.sainggik.feature.planning.main.PlanningViewModel
import id.buaja.sainggik.feature.planning.summary.BudgetSummaryViewModel
import id.buaja.sainggik.feature.saving.main.SavingViewModel
import id.buaja.sainggik.feature.transactions.manage.ManageTransactionViewModel
import id.buaja.sainggik.feature.transactions.main.TransactionsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SavingViewModel)
    viewModelOf(::PlanningViewModel)
    viewModelOf(::CategoryViewModel)

    viewModelOf(::TransactionsViewModel)
    viewModelOf(::ManageTransactionViewModel)

    viewModelOf(::ManageBudgetViewModel)
    viewModelOf(::BudgetSummaryViewModel)
    viewModelOf(::ListBudgetViewModel)

    viewModelOf(::HomeViewModel)
}