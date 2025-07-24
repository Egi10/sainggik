package id.buaja.sainggik.di

import id.buaja.sainggik.data.source.budgets.BudgetsLocalDataSource
import id.buaja.sainggik.data.source.budgets.BudgetsLocalDataSourceImpl
import id.buaja.sainggik.data.source.categories.CategoriesLocalDataSource
import id.buaja.sainggik.data.source.categories.CategoriesLocalDataSourceImpl
import id.buaja.sainggik.data.source.transactions.TransactionsLocalDataSource
import id.buaja.sainggik.data.source.transactions.TransactionsLocalDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::BudgetsLocalDataSourceImpl) {
        bind<BudgetsLocalDataSource>()
    }
    singleOf(::CategoriesLocalDataSourceImpl) {
        bind<CategoriesLocalDataSource>()
    }
    singleOf(::TransactionsLocalDataSourceImpl) {
        bind<TransactionsLocalDataSource>()
    }
}