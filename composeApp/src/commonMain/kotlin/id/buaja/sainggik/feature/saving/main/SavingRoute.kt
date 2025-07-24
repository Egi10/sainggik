package id.buaja.sainggik.feature.saving.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import id.buaja.sainggik.feature.saving.main.intent.SavingIntent
import id.buaja.sainggik.feature.saving.navigation.OnSavingDestination
import id.buaja.sainggik.feature.saving.navigation.SavingDestination
import id.buaja.sainggik.feature.saving.main.state.SavingSideEffect
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun SavingRoute(
    onSavingDestination: OnSavingDestination,
    modifier: Modifier = Modifier,
    viewModel: SavingViewModel = koinViewModel()
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(
            intent = SavingIntent.LoadInitialData
        )
    }

    viewModel.collectSideEffect {
        when(it) {
            is SavingSideEffect.NavigateToSavingHistory -> {
                onSavingDestination.invoke(
                    SavingDestination.NavigateToDetail(id = it.id)
                )
            }
        }
    }

    SavingScreen(
        modifier = modifier,
        onSavingIntent = viewModel::onIntent
    )
}