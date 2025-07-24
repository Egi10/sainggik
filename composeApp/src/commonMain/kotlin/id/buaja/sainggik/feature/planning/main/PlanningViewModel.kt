package id.buaja.sainggik.feature.planning.main

import id.buaja.sainggik.core.ui.base.BaseViewModel
import id.buaja.sainggik.feature.planning.main.intent.PlanningIntent
import id.buaja.sainggik.feature.planning.main.state.PlanningSideEffect
import id.buaja.sainggik.feature.planning.main.state.PlanningState

class PlanningViewModel : BaseViewModel<PlanningState, PlanningSideEffect>(initialState = PlanningState()) {

    fun onIntent(intent: PlanningIntent) {
        when (intent) {
            is PlanningIntent.ChangeTab -> {
                updateState {
                    copy(
                        selectedTab = intent.index
                    )
                }

                when (intent.index) {
                    0 -> {
                        sideEffect {
                            PlanningSideEffect.LoadCategoryData
                        }
                    }

                    1 -> {
                        sideEffect {
                            PlanningSideEffect.LoadBudget
                        }
                    }

                    2 -> {
                        sideEffect {
                            PlanningSideEffect.LoadSummary
                        }
                    }
                }
            }

            PlanningIntent.FabClicked -> {
                handleFabClick()
            }
        }
    }

    private fun handleFabClick() = intent {
        when (state.selectedTab) {
            0 -> {
                postSideEffect(
                    sideEffect = PlanningSideEffect.ShowAddCategoryBottomSheet
                )
            }

            1 -> {
                sideEffect {
                    PlanningSideEffect.NavigateManageBudget()
                }
            }
        }
    }
}