package id.buaja.sainggik.core.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Budget
import id.buaja.sainggik.core.designsystem.icon.collection.Home
import id.buaja.sainggik.core.designsystem.icon.collection.Settings
import id.buaja.sainggik.core.designsystem.icon.collection.Transactions
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview


@Stable
data class SainggikNavigationBarMenu(
    val icon: ImageVector,
    val label: String,
    val destination: Any,
)

@Composable
fun SainggikNavigationBar(
    items: List<SainggikNavigationBarMenu>,
    selected: SainggikNavigationBarMenu?,
    onItemClick: (SainggikNavigationBarMenu) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = SainggikTheme.colors.surface,
        modifier = modifier
    ) {
        Column {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = SainggikTheme.colors.outline
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
                    .windowInsetsPadding(
                        insets = WindowInsets.navigationBars
                    )
                    .defaultMinSize(minHeight = 75.dp)
                    .selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp
                ),
                content = {
                    items.forEach { item ->
                        SainggikNavigationBarItem(
                            modifier = Modifier
                                .weight(1f)
                                .rippleClickable(
                                    onClick = {
                                        onItemClick.invoke(item)
                                    }
                                )
                                .padding(
                                    vertical = 12.dp
                                ),
                            icon = item.icon,
                            label = item.label,
                            selected = selected == item
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun SainggikNavigationBarItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue = if (selected) {
            SainggikTheme.colors.onBackground
        } else {
            SainggikTheme.colors.onSurfaceVariant
        },
        label = "navigationItemColor"
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = animatedColor
        )

        Text(
            text = label,
            style = SainggikTheme.typography.caption,
            color = animatedColor
        )
    }
}

@Preview
@Composable
fun SainggikNavigationBarPreview() {
    SainggikTheme(
        darkTheme = false
    ) {
        Scaffold(
            containerColor = SainggikTheme.colors.background,
            bottomBar = {
                SainggikNavigationBar(
                    items = listOf(
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Home,
                            label = "Overview",
                            destination = ""
                        ),
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Transactions,
                            label = "Transactions",
                            destination = ""
                        ),
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Budget,
                            label = "Budget",
                            destination = ""
                        ),
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Settings,
                            label = "Settings",
                            destination = ""
                        )
                    ),
                    selected = SainggikNavigationBarMenu(
                        icon = SainggikIcon.Transactions,
                        label = "Transactions",
                        destination = ""
                    ),
                    onItemClick = {

                    }
                )
            }
        ) {

        }
    }
}

@Preview
@Composable
fun SainggikNavigationBarDarkPreview() {
    SainggikTheme(
        darkTheme = true
    ) {
        Scaffold(
            containerColor = SainggikTheme.colors.background,
            bottomBar = {
                SainggikNavigationBar(
                    items = listOf(
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Home,
                            label = "Overview",
                            destination = ""
                        ),
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Transactions,
                            label = "Transactions",
                            destination = ""
                        ),
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Budget,
                            label = "Budget",
                            destination = ""
                        ),
                        SainggikNavigationBarMenu(
                            icon = SainggikIcon.Settings,
                            label = "Settings",
                            destination = ""
                        )
                    ),
                    selected = SainggikNavigationBarMenu(
                        icon = SainggikIcon.Transactions,
                        label = "Transactions",
                        destination = ""
                    ),
                    onItemClick = {

                    }
                )
            }
        ) {

        }
    }
}