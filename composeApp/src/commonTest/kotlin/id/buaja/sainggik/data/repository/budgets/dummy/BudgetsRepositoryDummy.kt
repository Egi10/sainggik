package id.buaja.sainggik.data.repository.budgets.dummy

import id.buaja.sainggik.data.Budgets
import id.buaja.sainggik.data.SelectAllBudgets
import id.buaja.sainggik.domain.model.budgets.BudgetsParam

internal object BudgetsRepositoryDummy {
    val dummyBudgets = Budgets(
        id = 0L,
        category_id = 101L,
        start_date = "2025-06-01",
        end_date = "2025-06-30",
        amount_limit = 1_500_000.0,
        created_at = "",
        updated_at = ""
    )

    val dummyBudgetsParam = BudgetsParam(
        categoryId = 101L,
        startDate = "2025-06-01",
        endDate = "2025-06-30",
        amountLimit = 1_500_000.0
    )

    val dummySelectAllBudgetsList = listOf(
        SelectAllBudgets(
            id = 1L,
            category_id = 101L,
            start_date = "2025-06-01",
            end_date = "2025-06-30",
            amount_limit = 1_500_000.0,
            created_at = "2025-06-01T08:00:00"
        ),
        SelectAllBudgets(
            id = 2L,
            category_id = 102L,
            start_date = "2025-07-01",
            end_date = "2025-07-31",
            amount_limit = 2_000_000.0,
            created_at = "2025-07-01T08:00:00"
        )
    )

    val expectedBudgetLists = listOf(
        id.buaja.sainggik.domain.model.budgets.Budget(
            id = 1L,
            categoryId = 101L,
            startDate = "2025-06-01",
            endDate = "2025-06-30",
            amountLimit = 1_500_000.0,
            createdAt = "2025-06-01T08:00:00"
        ),
        id.buaja.sainggik.domain.model.budgets.Budget(
            id = 2L,
            categoryId = 102L,
            startDate = "2025-07-01",
            endDate = "2025-07-31",
            amountLimit = 2_000_000.0,
            createdAt = "2025-07-01T08:00:00"
        )
    )
}