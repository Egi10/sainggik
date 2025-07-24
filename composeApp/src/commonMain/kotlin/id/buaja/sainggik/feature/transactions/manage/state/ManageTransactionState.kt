package id.buaja.sainggik.feature.transactions.manage.state

import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.domain.model.category.Category
import kotlinx.datetime.LocalDate

data class ManageTransactionState(
    val availableCategories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val date: LocalDate = DateTimeHelper.getCurrentLocalDate()
)
