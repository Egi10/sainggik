package id.buaja.sainggik.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.components.SainggikModalBottomSheet
import id.buaja.sainggik.core.designsystem.components.SainggikScrollableTab
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.initials
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import id.buaja.sainggik.domain.model.category.Category
import id.buaja.sainggik.domain.model.category.TypeCategory
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.label_expenses
import sainggik.composeapp.generated.resources.label_income
import sainggik.composeapp.generated.resources.placeholder_select_category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChooseCategoryBottomSheet(
    category: List<Category>,
    onSelectedItem: (Category) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    SainggikModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        contentPadding = PaddingValues(
            vertical = 20.dp
        ),
        content = {
            ChooseCategoryContent(
                category = category,
                onSelectedItem = {
                    onSelectedItem(it)
                    onDismissRequest()
                }
            )
        }
    )
}

@Composable
private fun ChooseCategoryContent(
    category: List<Category>,
    onSelectedItem: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterTabs = listOf(
        stringResource(Res.string.label_expenses),
        stringResource(Res.string.label_income)
    )
    var selectedTab by remember {
        mutableStateOf(0)
    }
    val filterCategory by remember {
        derivedStateOf {
            if (selectedTab == 0) {
                category.filter { it.type == TypeCategory.EXPENSE }
            } else {
                category.filter { it.type == TypeCategory.INCOME }
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp
        )
    ) {
        Text(
            text = stringResource(Res.string.placeholder_select_category),
            style = SainggikTheme.typography.headline,
            color = SainggikTheme.colors.onBackground,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
        )

        SainggikScrollableTab(
            tabs = filterTabs,
            selectedTabIndex = selectedTab,
            onTabSelected = {
                selectedTab = it
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(
                items = filterCategory,
                key = {
                    it.id
                }
            ) {
                CategoryItem(
                    item = it,
                    modifier = Modifier
                        .rippleClickable(
                            onClick = {
                                onSelectedItem.invoke(it)
                            }
                        )
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        )
                )
            }
        }
    }
}

@Composable
private fun CategoryItem(
    item: Category,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = SainggikTheme.colors.surfaceVariant,
                    shape = SainggikTheme.shapes.small
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = item.name.initials(),
                style = SainggikTheme.typography.body,
                color = SainggikTheme.colors.onBackground,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier
                .width(16.dp)
        )

        Text(
            text = item.name,
            style = SainggikTheme.typography.body,
            color = SainggikTheme.colors.onBackground,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Preview
@Composable
private fun ChooseCategoryContentPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
                .padding(
                    vertical = 20.dp
                )
        ) {
            ChooseCategoryContent(
                category = dummyCategories,
                onSelectedItem = {

                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

private val dummyCategories = listOf(
    Category(
        id = 1L,
        name = "Salary",
        type = TypeCategory.INCOME
    ),
    Category(
        id = 2L,
        name = "Freelance",
        type = TypeCategory.INCOME
    ),
    Category(
        id = 3L,
        name = "Investment",
        type = TypeCategory.INCOME
    ),
    Category(
        id = 4L,
        name = "Food & Drinks",
        type = TypeCategory.EXPENSE
    ),
    Category(
        id = 5L,
        name = "Transportation",
        type = TypeCategory.EXPENSE
    ),
    Category(
        id = 6L,
        name = "Entertainment",
        type = TypeCategory.EXPENSE
    ),
    Category(
        id = 7L,
        name = "Shopping",
        type = TypeCategory.EXPENSE
    ),
    Category(
        id = 8L,
        name = "Health",
        type = TypeCategory.EXPENSE
    ),
    Category(
        id = 9L,
        name = "Gift",
        type = TypeCategory.INCOME
    )
)
