package id.buaja.sainggik.feature.saving.main.state

sealed class SavingSideEffect {
    data class NavigateToSavingHistory(val id: Int) : SavingSideEffect()
}