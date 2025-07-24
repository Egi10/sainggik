package id.buaja.sainggik.feature.saving.main.intent

typealias OnSavingIntent = (SavingIntent) -> Unit

sealed interface SavingIntent {
    data object LoadInitialData : SavingIntent
    data class OpenDetail(val id: Int) : SavingIntent
}