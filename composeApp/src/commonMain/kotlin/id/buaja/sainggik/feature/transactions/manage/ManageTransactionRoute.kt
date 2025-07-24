package id.buaja.sainggik.feature.transactions.manage

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.ui.CalendarBottomSheets
import id.buaja.sainggik.core.ui.ChooseCategoryBottomSheet
import id.buaja.sainggik.feature.transactions.manage.intent.ManageTransactionIntent
import id.buaja.sainggik.feature.transactions.manage.navigation.ManageTransactionsDestination
import id.buaja.sainggik.feature.transactions.manage.navigation.OnManageTransactionsDestination
import id.buaja.sainggik.feature.transactions.manage.state.ManageTransactionSideEffect
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ManageTransactionRoute(
    onManageTransactionsDestination: OnManageTransactionsDestination,
    modifier: Modifier = Modifier,
    viewModel: ManageTransactionViewModel = koinViewModel(),
    transactionId: Long? = null,
) {
    val state by viewModel.collectAsState()

    var showDateBottomSheet by remember {
        mutableStateOf(false)
    }
    var showChooseCategoryBottomSheet by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.onIntent(
            intent = ManageTransactionIntent.Initial
        )
    }

    LaunchedEffect(transactionId) {
        if (transactionId != null) {
            viewModel.onIntent(
                intent = ManageTransactionIntent.InitialTransaction(
                    transactionsId = transactionId
                )
            )
        }
    }

    viewModel.collectSideEffect {
        when (it) {
            ManageTransactionSideEffect.NavigateBack -> {
                onManageTransactionsDestination.invoke(ManageTransactionsDestination.NavigationBack)
            }

            ManageTransactionSideEffect.ShowLoadingDialog -> {
                // TODO Add Loading
            }

            is ManageTransactionSideEffect.ShowToastMessage -> {
                // TODO Add Message
            }

            is ManageTransactionSideEffect.ShowCategoryBottomSheet -> {
                showChooseCategoryBottomSheet = true
            }

            is ManageTransactionSideEffect.ShowDateBottomSheet -> {
                showDateBottomSheet = true
            }
        }
    }

    ManageTransactionScreen(
        amount = viewModel.amount,
        note = viewModel.note,
        state = state,
        isVisibleTransaction = viewModel.amount.isNotEmpty()
                && viewModel.note.isNotEmpty()
                && state.selectedCategory != null,
        onManageTransactionIntent = viewModel::onIntent,
        transactionId = transactionId,
        modifier = modifier
    )

    if (showDateBottomSheet) {
        CalendarBottomSheets(
            onDismissRequest = {
                showDateBottomSheet = false
            },
            onDateSelected = {
                viewModel.onIntent(
                    intent = ManageTransactionIntent.OnDateSelected(it)
                )
            },
            selectedDate = state.date,
            maxDate = DateTimeHelper.getCurrentLocalDate()
        )
    }

    if (showChooseCategoryBottomSheet) {
        ChooseCategoryBottomSheet(
            category = state.availableCategories,
            onDismissRequest = {
                showChooseCategoryBottomSheet = false
            },
            onSelectedItem = {
                viewModel.onIntent(
                    intent = ManageTransactionIntent.OnCategorySelected(it)
                )
            }
        )
    }
}