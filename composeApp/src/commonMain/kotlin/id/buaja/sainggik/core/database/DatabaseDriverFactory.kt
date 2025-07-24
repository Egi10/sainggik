package id.buaja.sainggik.core.database

import app.cash.sqldelight.db.SqlDriver

const val DATABASE_NAME = "sainggik.db"

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}