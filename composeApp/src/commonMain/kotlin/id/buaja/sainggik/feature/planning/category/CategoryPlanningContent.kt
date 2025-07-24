package id.buaja.sainggik.feature.planning.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Empty
import id.buaja.sainggik.core.designsystem.icon.collection.Error
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.initials
import id.buaja.sainggik.core.ui.MessageFeedback
import id.buaja.sainggik.core.ui.SwipeableListItem
import id.buaja.sainggik.domain.model.category.Category
import id.buaja.sainggik.domain.model.category.CategoryGroup
import id.buaja.sainggik.domain.model.category.TypeCategory
import id.buaja.sainggik.feature.planning.category.intent.CategoryIntent
import id.buaja.sainggik.feature.planning.category.intent.OnCategoryIntent
import id.buaja.sainggik.feature.planning.category.state.ListCategoryState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun CategoryPlanningContent(
    state: ListCategoryState,
    onCategoryIntent: OnCategoryIntent,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> {
            // TODO: Show Loading Indicator
        }

        !state.errorMessage.isNullOrEmpty() -> {
            MessageFeedback(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 24.dp,
                        horizontal = 16.dp
                    ),
                image = SainggikIcon.Error,
                title = "Empty Categories ${state.errorMessage}",
                description = "It looks quiet here. Add your firs"
            )
        }

        state.data.isEmpty() -> {
            MessageFeedback(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 24.dp,
                        horizontal = 16.dp
                    ),
                image = SainggikIcon.Empty,
                title = "Empty Categories",
                description = "It looks quiet here. Add your first category to get started."
            )
        }

        else -> {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(
                    vertical = 8.dp
                ),
                content = {
                    state.data.forEach { group ->
                        stickyHeader {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = SainggikTheme.colors.background
                                    )
                                    .padding(
                                        top = 16.dp,
                                        bottom = 8.dp,
                                        start = 16.dp,
                                        end = 16.dp
                                    )
                            ) {
                                Text(
                                    text = when (group.typeCategory) {
                                        TypeCategory.INCOME -> "Income"
                                        TypeCategory.EXPENSE -> "Expense"
                                    },
                                    style = SainggikTheme.typography.title,
                                    color = SainggikTheme.colors.onBackground
                                )
                            }
                        }

                        items(
                            items = group.category,
                            key = { it.id }
                        ) {
                            SwipeableListItem(
                                item = it,
                                onDelete = { item ->
                                    onCategoryIntent.invoke(
                                        CategoryIntent.DeleteCategory(
                                            id = item.id
                                        )
                                    )
                                },
                                onEdit = { item ->
                                    onCategoryIntent.invoke(
                                        CategoryIntent.EditCategory(
                                            category = item
                                        )
                                    )
                                },
                                content = { item ->
                                    CategoryPlanningItem(
                                        item = it,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = SainggikTheme.colors.background
                                            )
                                            .padding(
                                                vertical = 8.dp,
                                                horizontal = 16.dp
                                            )
                                    )
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun CategoryPlanningItem(
    item: Category,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = SainggikTheme.colors.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = item.name.initials(),
                style = SainggikTheme.typography.label,
                color = SainggikTheme.colors.onBackground
            )
        }

        Spacer(
            modifier = Modifier
                .width(16.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .align(
                    alignment = Alignment.CenterVertically
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp
            )
        ) {
            Text(
                text = item.name,
                style = SainggikTheme.typography.body,
                fontWeight = FontWeight.Medium,
                color = SainggikTheme.colors.onBackground
            )

            Text(
                text = when (item.type) {
                    TypeCategory.INCOME -> "Income"
                    TypeCategory.EXPENSE -> "Expense"
                },
                style = SainggikTheme.typography.label,
                fontWeight = FontWeight.Normal,
                color = SainggikTheme.colors.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun CategoryPlanningContentPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
        ) {
            CategoryPlanningContent(
                state = ListCategoryState(
                    isLoading = false,
                    data = dummyCategories.groupBy {
                        it.type
                    }.map { (label, items) ->
                        CategoryGroup(
                            typeCategory = label,
                            category = items
                        )
                    },
                    errorMessage = null
                ),
                onCategoryIntent = {}
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