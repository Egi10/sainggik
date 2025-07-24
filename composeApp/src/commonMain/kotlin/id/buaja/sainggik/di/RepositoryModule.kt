package id.buaja.sainggik.di

import id.buaja.sainggik.data.repository.budgets.BudgetsRepositoryImpl
import id.buaja.sainggik.data.repository.categories.CategoriesRepositoryImpl
import id.buaja.sainggik.data.repository.transactions.TransactionsRepositoryImpl
import id.buaja.sainggik.domain.repository.BudgetsRepository
import id.buaja.sainggik.domain.repository.CategoriesRepository
import id.buaja.sainggik.domain.repository.TransactionsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::BudgetsRepositoryImpl) {
        bind<BudgetsRepository>()
    }
    singleOf(::CategoriesRepositoryImpl) {
        bind<CategoriesRepository>()
    }
    singleOf(::TransactionsRepositoryImpl) {
        bind<TransactionsRepository>()
    }
}