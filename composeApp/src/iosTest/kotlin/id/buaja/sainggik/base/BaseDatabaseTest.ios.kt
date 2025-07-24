package id.buaja.sainggik.base

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import id.buaja.sainggik.SainggikDatabase

actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(
        schema = SainggikDatabase.Schema,
        name = "sampledb",
        onConfiguration = {
            it.copy(
                inMemory = true
            )
        }
    )
}