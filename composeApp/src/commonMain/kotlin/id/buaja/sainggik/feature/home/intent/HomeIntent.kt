package id.buaja.sainggik.feature.home.intent

import id.buaja.sainggik.feature.home.state.HomeFilterState

typealias OnHomeIntent = (HomeIntent) -> Unit

sealed interface HomeIntent {
    data class InitialFilter(val filters: List<HomeFilterState>) : HomeIntent
    data class OnChangeFilter(val filter: HomeFilterState) : HomeIntent
    data object OnDetailBudgetAlertClick : HomeIntent
    data object OnDetailSpendingOverviewClick : HomeIntent
}