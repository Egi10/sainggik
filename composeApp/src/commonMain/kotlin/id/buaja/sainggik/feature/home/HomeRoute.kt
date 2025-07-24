package id.buaja.sainggik.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import id.buaja.sainggik.feature.home.intent.HomeIntent
import id.buaja.sainggik.feature.home.state.HomeFilterEnum
import id.buaja.sainggik.feature.home.state.HomeFilterState
import org.jetbrains.compose.resources.getString
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.filter_custom
import sainggik.composeapp.generated.resources.filter_this_month
import sainggik.composeapp.generated.resources.filter_this_week

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(state.filter) {
        viewModel.onIntent(
            intent = HomeIntent.InitialFilter(
                filters = listOf(
                    HomeFilterState(
                        key = HomeFilterEnum.THIS_WEEK,
                        value = getString(Res.string.filter_this_week)
                    ),
                    HomeFilterState(
                        key = HomeFilterEnum.THIS_MONTH,
                        value = getString(Res.string.filter_this_month)
                    ),
                    // TODO : Rilis Dulu
//                    HomeFilterState(
//                        key = HomeFilterEnum.CUSTOM,
//                        value = getString(Res.string.filter_custom)
//                    )
                )
            )
        )
    }

    HomeScreen(
        filterSelectable = state.filterSelected,
        filters = state.filter,
        transactionSummaryState = state.transactionSummaryState,
        transactionSpendingState = state.transactionSpendingState,
        transactionDailySpendingState = state.transactionDailySpendingState,
        budgetsExceedingState = state.budgetsExceedingState,
        onHomeIntent = viewModel::onIntent,
        modifier = modifier
    )
}