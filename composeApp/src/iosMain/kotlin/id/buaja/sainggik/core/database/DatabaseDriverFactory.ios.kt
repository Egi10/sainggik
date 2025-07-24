package id.buaja.sainggik.core.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import id.buaja.sainggik.SainggikDatabase

class IosDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = SainggikDatabase.Schema.synchronous(),
            name = DATABASE_NAME
        )
    }
}