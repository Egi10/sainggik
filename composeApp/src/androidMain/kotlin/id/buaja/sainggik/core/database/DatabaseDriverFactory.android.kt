package id.buaja.sainggik.core.database

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import id.buaja.sainggik.SainggikDatabase

class AndroidDatabaseDriverFactory(
    private val context: Context
): DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = SainggikDatabase.Schema.synchronous(),
            context = context,
            name = DATABASE_NAME
        )
    }
}
