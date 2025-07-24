package id.buaja.sainggik.feature.saving.navigation

typealias OnSavingDestination = (SavingDestination) -> Unit

sealed interface SavingDestination {
    data class NavigateToDetail(val id: Int): SavingDestination
}