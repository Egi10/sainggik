package id.buaja.sainggik

import android.app.Application
import id.buaja.sainggik.di.KoinInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SainggikApp : Application() {
    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init {
            androidContext(
                androidContext = this@SainggikApp
            )
            androidLogger()
        }
    }
}