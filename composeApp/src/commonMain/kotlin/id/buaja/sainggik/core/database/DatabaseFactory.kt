package id.buaja.sainggik.core.database

import app.cash.sqldelight.EnumColumnAdapter
import id.buaja.sainggik.SainggikDatabase
import id.buaja.sainggik.data.Categories

object DatabaseFactory {
    fun createDatabase(driverFactory: DatabaseDriverFactory): SainggikDatabase {
        val driver = driverFactory.createDriver()

        return SainggikDatabase.invoke(
            driver = driver,
            categoriesAdapter = Categories.Adapter(
                typeAdapter = EnumColumnAdapter()
            )
        )
    }
}