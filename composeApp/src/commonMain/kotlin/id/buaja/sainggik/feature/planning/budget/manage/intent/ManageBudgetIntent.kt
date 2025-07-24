package id.buaja.sainggik.feature.planning.budget.manage.intent

import id.buaja.sainggik.domain.model.category.Category
import kotlinx.datetime.LocalDate

typealias OnManageBudgetIntent = (ManageBudgetIntent) -> Unit

sealed interface ManageBudgetIntent {
    data object Initial : ManageBudgetIntent
    data class InitialBudget(val budgetId: Long) : ManageBudgetIntent
    data object OnBackClick : ManageBudgetIntent

    data object OnCategoryClick : ManageBudgetIntent
    data class OnCategorySelected(val category: Category) : ManageBudgetIntent
    data class OnBudgetAmountChange(val amount: String) : ManageBudgetIntent
    data object OnStartDateClick : ManageBudgetIntent
    data class OnStartDateSelected(val date: LocalDate) : ManageBudgetIntent
    data object OnEndDateClick : ManageBudgetIntent
    data class OnEndDateSelected(val date: LocalDate) : ManageBudgetIntent

    data object OnSubmitClick : ManageBudgetIntent
}