package id.buaja.sainggik.core.ui.base

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.SettingsBuilder
import org.orbitmvi.orbit.syntax.Syntax
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any>(
    initialState: STATE,
    buildSettings: SettingsBuilder.() -> Unit = {},
    onCreate: (suspend Syntax<STATE, SIDE_EFFECT>.() -> Unit)? = null
) : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

    protected fun updateState(reducer: STATE.() -> STATE) {
        intent {
            reduce {
                reducer(state)
            }
        }
    }

    protected fun sideEffect(block: (STATE) -> SIDE_EFFECT) {
        intent {
            val currentState = state
            val sideEffect = block(currentState)
            postSideEffect(sideEffect)
        }
    }

    override val container = container(
        initialState = initialState,
        buildSettings = buildSettings,
        onCreate = onCreate
    )
}
