package id.buaja.sainggik.base

import app.cash.sqldelight.db.SqlDriver
import id.buaja.sainggik.core.database.DatabaseDriverFactory
import id.buaja.sainggik.core.database.DatabaseFactory
import kotlin.test.AfterTest

/**
 * Init driver for each platform. Should *always* be called to setup test
 */
expect fun createDriver(): SqlDriver

class TestDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return id.buaja.sainggik.base.createDriver()
    }
}

open class BaseDatabaseTest {
    private val driver: SqlDriver by lazy {
        TestDatabaseDriverFactory().createDriver()
    }

    val database by lazy {
        DatabaseFactory.createDatabase(
            driverFactory = TestDatabaseDriverFactory()
        )
    }

    @AfterTest
    fun afterTest() {
        driver.close()
    }
}