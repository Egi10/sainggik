package id.buaja.sainggik.di

import id.buaja.sainggik.SainggikDatabase
import id.buaja.sainggik.core.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformAndroidIosDatabaseDriverFactoryModule: Module

val databaseModule = module {
    includes(platformAndroidIosDatabaseDriverFactoryModule)

    single {
        DatabaseFactory.createDatabase(
            driverFactory = get()
        )
    }

    single {
        get<SainggikDatabase>().budgetsQueries
    }

    single {
        get<SainggikDatabase>().transactionsQueries
    }

    single {
        get<SainggikDatabase>().categoriesQueries
    }
}