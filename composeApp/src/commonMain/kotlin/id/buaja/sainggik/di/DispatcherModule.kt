package id.buaja.sainggik.di

import id.buaja.sainggik.core.utils.DefaultDispatcherProvider
import id.buaja.sainggik.core.utils.DispatcherProvider
import org.koin.dsl.module

val dispatcherModule = module {
    single<DispatcherProvider> {
        DefaultDispatcherProvider()
    }
}