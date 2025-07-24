package id.buaja.sainggik.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

object KoinInitializer {
    fun init(config: KoinAppDeclaration? = null) = startKoin {
        includes(config)
        modules(
            modules = listOf(
                dispatcherModule,
                databaseModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        )
    }
}