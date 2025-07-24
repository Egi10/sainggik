package id.buaja.sainggik.di

import id.buaja.sainggik.core.database.AndroidDatabaseDriverFactory
import id.buaja.sainggik.core.database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformAndroidIosDatabaseDriverFactoryModule: Module = module {
    singleOf(::AndroidDatabaseDriverFactory) {
        bind<DatabaseDriverFactory>()
    }
}