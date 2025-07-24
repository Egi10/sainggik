package id.buaja.sainggik.feature.planning.main.intent

typealias OnPlanningIntent = (PlanningIntent) -> Unit

sealed interface PlanningIntent {
    data class ChangeTab(val index: Int) : PlanningIntent
    data object FabClicked : PlanningIntent
}