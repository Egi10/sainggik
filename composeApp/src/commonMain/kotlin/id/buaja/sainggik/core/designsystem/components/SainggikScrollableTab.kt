package id.buaja.sainggik.core.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SainggikScrollableTab(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Trigger default selection to index 1 only on first launch
    LaunchedEffect(Unit) {
        if (selectedTabIndex == 0 && tabs.size > 1) {
            onTabSelected(0)
        }
    }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = 16.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                SainggikTab(
                    modifier = Modifier.padding(end = 32.dp),
                    text = title,
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) }
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = SainggikTheme.colors.outline
        )
    }
}

@Composable
fun SainggikTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var textWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    // Animasi untuk lebar indicator
    val animatedIndicatorWidth by animateDpAsState(
        targetValue = if (selected) {
            textWidth
        } else {
            0.dp
        },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "indicator_width"
    )

    // Animasi untuk warna text
    val animatedTextColor by animateColorAsState(
        targetValue = if (selected) {
            SainggikTheme.colors.onBackground
        } else {
            SainggikTheme.colors.onSurfaceVariant
        },
        animationSpec = tween(durationMillis = 200),
        label = "text_color"
    )

    Column(
        modifier = modifier
            .rippleClickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        Text(
            text = text,
            style = SainggikTheme.typography.label,
            fontWeight = FontWeight.Bold,
            color = animatedTextColor,
            onTextLayout = { textLayoutResult ->
                textWidth = with(density) {
                    textLayoutResult.size.width.toDp()
                }
            }
        )

        Spacer(
            modifier = Modifier
                .height(13.dp)
        )

        Box(
            modifier = Modifier
                .width(animatedIndicatorWidth)
                .height(2.dp)
                .background(SainggikTheme.colors.primary)
        )
    }
}

@Preview
@Composable
private fun SainggikScrollableTabPreview() {
    SainggikTheme {
        var selectedTab by rememberSaveable { mutableStateOf(0) }
        val tabItems = listOf("Expenses", "Income", "Pengeluaran Makan Mingguan", "Bulanan", "Lorem", "Ipsum")

        Box(
            modifier = Modifier
                .statusBarsPadding()
                .background(
                    color = SainggikTheme.colors.background
                )
                .padding(
                    bottom = 45.dp
                )
        ) {
            SainggikScrollableTab(
                tabs = tabItems,
                selectedTabIndex = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    }
}

@Preview
@Composable
private fun SainggikScrollableTabDarkPreview() {
    SainggikTheme(
        darkTheme = true
    ) {
        var selectedTab by rememberSaveable { mutableStateOf(0) }
        val tabItems = listOf("Expenses", "Income", "Pengeluaran Makan Mingguan", "Bulanan", "Lorem", "Ipsum")

        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
                .padding(
                    bottom = 45.dp
                )
        ) {
            SainggikScrollableTab(
                tabs = tabItems,
                selectedTabIndex = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    }
}