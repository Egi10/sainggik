package id.buaja.sainggik.data.source.budgets

import app.cash.turbine.test
import id.buaja.sainggik.base.BaseDatabaseTest
import id.buaja.sainggik.base.TestDispatcherProvider
import id.buaja.sainggik.data.source.budgets.dummy.BudgetsLocalDataSourceDummy
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BudgetsLocalDataSourceImplTest : BaseDatabaseTest() {
    private val budgetsQueries by lazy {
        database.budgetsQueries
    }

    private val budgetLocalDataSourceImpl by lazy {
        BudgetsLocalDataSourceImpl(
            budgetsQueries = budgetsQueries,
            dispatcherProvider = TestDispatcherProvider()
        )
    }

    @Test
    fun `when success save budget`() = runTest {
        budgetLocalDataSourceImpl.insertBudget(
            budgets = BudgetsLocalDataSourceDummy.dummyBudgets
        )

        budgetLocalDataSourceImpl.getAllBudgetsWithCategory().test {
            assertEquals(BudgetsLocalDataSourceDummy.expectSelectAllBudgets, awaitItem())
        }
    }
}