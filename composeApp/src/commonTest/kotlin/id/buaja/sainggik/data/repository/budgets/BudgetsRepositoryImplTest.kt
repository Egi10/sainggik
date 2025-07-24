package id.buaja.sainggik.data.repository.budgets

import app.cash.turbine.test
import dev.mokkery.*
import dev.mokkery.answering.returns
import dev.mokkery.verify.VerifyMode
import id.buaja.sainggik.data.repository.budgets.dummy.BudgetsRepositoryDummy
import id.buaja.sainggik.data.source.budgets.BudgetsLocalDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BudgetsRepositoryImplTest {
    val budgetsLocalDataSource = mock<BudgetsLocalDataSource>()

    private val budgetsRepositoryImpl by lazy {
        BudgetsRepositoryImpl(
            budgetsLocalDataSource = budgetsLocalDataSource
        )
    }

    @Test
    fun `when success set budget`() = runTest {
        everySuspend {
            budgetsLocalDataSource.insertBudget(
                budgets = BudgetsRepositoryDummy.dummyBudgets
            )
        } returns 1

        val result = budgetsRepositoryImpl.setBudget(
            param = BudgetsRepositoryDummy.dummyBudgetsParam
        )
        assertEquals(1, result)

        verifySuspend(mode = VerifyMode.exactly(1)) {
            budgetsLocalDataSource.insertBudget(
                budgets = BudgetsRepositoryDummy.dummyBudgets
            )
        }
    }

    @Test
    fun `when success get budgets`() = runTest {
        every {
            budgetsLocalDataSource.getAllBudgetsWithCategory()
        } returns flowOf(
            BudgetsRepositoryDummy.dummySelectAllBudgetsList
        )

        budgetsRepositoryImpl.getAllBudgetsWithCategory().test {
            assertEquals(BudgetsRepositoryDummy.expectedBudgetLists, awaitItem())
            awaitComplete()
        }

        verify(mode = VerifyMode.exactly(1)) {
            budgetsLocalDataSource.getAllBudgetsWithCategory()
        }
    }
}