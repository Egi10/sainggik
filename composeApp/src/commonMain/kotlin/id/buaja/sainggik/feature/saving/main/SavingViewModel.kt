package id.buaja.sainggik.feature.saving.main

import androidx.lifecycle.ViewModel
import id.buaja.sainggik.feature.saving.main.intent.SavingIntent
import id.buaja.sainggik.feature.saving.main.state.SavingSideEffect
import id.buaja.sainggik.feature.saving.main.state.SavingState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SavingViewModel : ViewModel(), ContainerHost<SavingState, SavingSideEffect> {

    override val container: Container<SavingState, SavingSideEffect> = container(
        initialState = SavingState()
    )

    fun onIntent(intent: SavingIntent) = intent {
        when (intent) {
            SavingIntent.LoadInitialData -> {
                reduce {
                    state.copy(
                        total = 20
                    )
                }
            }

            is SavingIntent.OpenDetail -> {
                postSideEffect(
                    sideEffect = SavingSideEffect.NavigateToSavingHistory(
                        id = intent.id
                    )
                )
            }
        }
    }
}