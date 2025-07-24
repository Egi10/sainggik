package id.buaja.sainggik.feature.planning.budget.manage.state

import id.buaja.sainggik.domain.model.category.Category
import kotlinx.datetime.LocalDate

data class ManageBudgetState(
    val availableCategories: List<Category> = emptyList(),
    val category: Category? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null
)
