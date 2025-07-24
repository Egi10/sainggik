package id.buaja.sainggik.feature.planning.budget.list.intent

typealias OnBudgetListIntent = (BudgetListIntent) -> Unit

sealed interface BudgetListIntent {
    data object Initial : BudgetListIntent
    data class DeleteBudget(val id: Long) : BudgetListIntent
    data class EditBudget(val id: Long) : BudgetListIntent
}