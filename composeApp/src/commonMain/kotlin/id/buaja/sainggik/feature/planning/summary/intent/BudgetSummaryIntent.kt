package id.buaja.sainggik.feature.planning.summary.intent

sealed interface BudgetSummaryIntent {
    data object Initial : BudgetSummaryIntent
}