package id.buaja.sainggik.base

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import id.buaja.sainggik.SainggikDatabase

actual fun createDriver(): SqlDriver {
    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    SainggikDatabase.Schema.create(driver)
    return driver
}