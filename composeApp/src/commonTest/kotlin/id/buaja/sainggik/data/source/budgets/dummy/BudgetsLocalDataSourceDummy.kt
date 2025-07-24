package id.buaja.sainggik.data.source.budgets.dummy

import id.buaja.sainggik.data.Budgets
import id.buaja.sainggik.data.SelectAllBudgets

object BudgetsLocalDataSourceDummy {
    val dummyBudgets = Budgets(
        id = 1L,
        category_id = 101L,
        start_date = "2025-06-01",
        end_date = "2025-06-30",
        amount_limit = 1_500_000.0,
        created_at = "2025-06-01T08:00:00",
        updated_at = "2025-06-10T10:00:00"
    )

    val expectSelectAllBudgets = listOf(
        SelectAllBudgets(
            id = 1L,
            category_id = 101L,
            start_date = "2025-06-01",
            end_date = "2025-06-30",
            amount_limit = 1_500_000.0,
            created_at = "2025-06-01T08:00:00"
        )
    )
}