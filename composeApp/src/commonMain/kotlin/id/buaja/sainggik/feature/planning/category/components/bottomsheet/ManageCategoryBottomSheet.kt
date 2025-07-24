package id.buaja.sainggik.feature.planning.category.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.components.SainggikButton
import id.buaja.sainggik.core.designsystem.components.SainggikModalBottomSheet
import id.buaja.sainggik.core.designsystem.components.SainggikTextField
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import id.buaja.sainggik.feature.planning.category.intent.CategoryIntent
import id.buaja.sainggik.feature.planning.category.intent.OnCategoryIntent
import id.buaja.sainggik.feature.planning.category.state.ManageCategoryState
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCategoryBottomSheet(
    name: String,
    manageCategoryState: ManageCategoryState,
    onCategoryIntent: OnCategoryIntent,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    SainggikModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        content = {
            ManageCategoryContent(
                name = name,
                manageCategoryState = manageCategoryState,
                onCategoryIntent = onCategoryIntent
            )
        }
    )
}

@Composable
fun ManageCategoryContent(
    name: String,
    manageCategoryState: ManageCategoryState,
    onCategoryIntent: OnCategoryIntent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 24.dp
        )
    ) {
        Text(
            text = if (manageCategoryState.isUpdate) {
                "Edit New Category"
            } else {
                "Add New Category"
            },
            style = SainggikTheme.typography.headline,
            color = SainggikTheme.colors.onBackground
        )

        SainggikTextField(
            label = "Category Name",
            placeholder = "Enter Category Name",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            value = name,
            onValueChange = {
                onCategoryIntent.invoke(CategoryIntent.UpdateCategoryName(it))
            }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp
            )
        ) {
            ChangeBox(
                label = "Income",
                color = when (manageCategoryState.categoryType) {
                    ManageCategoryState.CategoryType.INCOME -> SainggikTheme.colors.primary
                    else -> SainggikTheme.colors.outline
                },
                onClick = {
                    onCategoryIntent.invoke(
                        CategoryIntent.UpdateCategoryType(
                            type = ManageCategoryState.CategoryType.INCOME
                        )
                    )
                }
            )

            ChangeBox(
                label = "Expense",
                color = when (manageCategoryState.categoryType) {
                    ManageCategoryState.CategoryType.EXPENSE -> SainggikTheme.colors.primary
                    else -> SainggikTheme.colors.outline
                },
                onClick = {
                    onCategoryIntent.invoke(
                        CategoryIntent.UpdateCategoryType(
                            type = ManageCategoryState.CategoryType.EXPENSE
                        )
                    )
                }
            )
        }

        SainggikButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = if (manageCategoryState.isUpdate) {
                "Edit Category"
            } else {
                "Save Category"
            },
            onClick = {
                onCategoryIntent.invoke(CategoryIntent.SubmitCategory)
            }
        )
    }
}

@Composable
private fun ChangeBox(
    label: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(12.dp)
            )
            .rippleClickable(
                onClick = onClick
            )
            .padding(
                all = 16.dp
            )
    ) {
        Text(
            text = label,
            style = SainggikTheme.typography.label,
            color = SainggikTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
private fun AddCategoryContentPreview() {
    SainggikTheme {
        Box(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        ) {
            ManageCategoryContent(
                name = "",
                manageCategoryState = ManageCategoryState(),
                onCategoryIntent = {}
            )
        }
    }
}