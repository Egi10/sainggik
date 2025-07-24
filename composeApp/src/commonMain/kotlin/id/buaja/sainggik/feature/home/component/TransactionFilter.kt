package id.buaja.sainggik.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.components.SainggikChips
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.feature.home.intent.HomeIntent
import id.buaja.sainggik.feature.home.intent.OnHomeIntent
import id.buaja.sainggik.feature.home.state.HomeFilterEnum
import id.buaja.sainggik.feature.home.state.HomeFilterState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun TransactionFilter(
    filters: List<HomeFilterState>,
    filterSelectable: HomeFilterState?,
    onHomeIntent: OnHomeIntent,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp
        ),
        contentPadding = PaddingValues(
            all = 12.dp
        )
    ) {
        items(
            items = filters,
            key = {
                it.key
            }
        ) {
            SainggikChips(
                selected = filterSelectable?.key == it.key,
                label = it.value,
                onClick = {
                    onHomeIntent.invoke(HomeIntent.OnChangeFilter(it))
                }
            )
        }
    }
}

@Preview
@Composable
private fun TransactionFilterPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            TransactionFilter(
                filterSelectable = HomeFilterState(
                    key = HomeFilterEnum.THIS_WEEK,
                    value = "Minggu ini"
                ),
                filters = listOf(
                    HomeFilterState(
                        key = HomeFilterEnum.THIS_WEEK,
                        value = "Minggu ini"
                    ),
                    HomeFilterState(
                        key = HomeFilterEnum.THIS_MONTH,
                        value = "Bulan ini"
                    ),
                    HomeFilterState(
                        key = HomeFilterEnum.CUSTOM,
                        value = "Kustom Tanggal"
                    )
                ),
                onHomeIntent = {}
            )
        }
    }
}