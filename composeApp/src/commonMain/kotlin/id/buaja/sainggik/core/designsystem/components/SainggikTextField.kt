package id.buaja.sainggik.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.Dropdown
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SainggikTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    BasicSainggikTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        modifier = modifier,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}

@Composable
fun SainggikSelectableTextField(
    value: String,
    label: String,
    placeholder: String,
    trailingIcon: ImageVector,
    onTrailingIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    BasicSainggikTextField(
        value = value,
        onValueChange = {},
        label = label,
        placeholder = placeholder,
        modifier = modifier,
        enabled = enabled,
        readOnly = true,
        trailingIcon = trailingIcon,
        onTrailingIconClick = onTrailingIconClick
    )
}

@Composable
fun SainggikTextAreaField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicSainggikTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        modifier = modifier,
        isTextArea = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}

@Composable
private fun BasicSainggikTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit) = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isTextArea: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        Text(
            text = label,
            style = SainggikTheme.typography.body,
            fontWeight = FontWeight.Medium,
            color = SainggikTheme.colors.onBackground
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = SainggikTheme.typography.label.copy(
                color = SainggikTheme.colors.onBackground
            ),
            readOnly = readOnly,
            enabled = enabled,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(SainggikTheme.colors.primary),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(
                            minHeight = if (isTextArea) {
                                144.dp
                            } else {
                                56.dp
                            }
                        )
                        .background(
                            color = SainggikTheme.colors.surfaceVariant,
                            shape = SainggikTheme.shapes.medium
                        ),
                    contentAlignment = if (isTextArea) {
                        Alignment.TopStart
                    } else {
                        Alignment.CenterStart
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp
                            ),
                        verticalAlignment = if (isTextArea) {
                            Alignment.Top
                        } else {
                            Alignment.CenterVertically
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    vertical = 16.dp
                                ),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = SainggikTheme.typography.body,
                                    color = SainggikTheme.colors.onSurfaceVariant
                                )
                            }

                            innerTextField()
                        }


                        if (trailingIcon != null) {
                            Box(
                                modifier = Modifier
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topEnd = 12.dp,
                                            bottomEnd = 12.dp
                                        )
                                    )
                                    .defaultMinSize(
                                        minWidth = 40.dp,
                                        minHeight = 56.dp
                                    )
                                    .then(
                                        other = if (enabled) {
                                            Modifier.rippleClickable(
                                                onClick = {
                                                    onTrailingIconClick.invoke()
                                                }
                                            )
                                        } else {
                                            Modifier
                                        }
                                    ),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(
                                    imageVector = trailingIcon,
                                    contentDescription = label,
                                    tint = SainggikTheme.colors.onBackground
                                )
                            }
                        } else {
                            Spacer(
                                modifier = Modifier
                                    .width(16.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun SainggikTextFieldPreview() {
    SainggikTheme(
        darkTheme = false
    ) {
        var tes by remember {
            mutableStateOf("")
        }
        var tes1 by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 24.dp
            )
        ) {
            SainggikTextField(
                value = tes1,
                onValueChange = {
                    tes1 = it
                },
                label = "Amount",
                placeholder = "Placeholder",
                modifier = Modifier
                    .fillMaxWidth()
            )

            SainggikSelectableTextField(
                value = "",
                label = "Category",
                placeholder = "Placeholder",
                trailingIcon = SainggikIcon.Dropdown,
                onTrailingIconClick = {

                }
            )

            SainggikTextAreaField(
                value = tes,
                onValueChange = {
                    tes = it
                },
                label = "Notes",
                placeholder = "Placeholder",
            )
        }
    }
}

@Preview
@Composable
private fun SainggikTextFieldDarkPreview() {
    SainggikTheme(
        darkTheme = true
    ) {
        var tes by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .background(
                    color = SainggikTheme.colors.background
                )
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 24.dp
            )
        ) {
            SainggikTextField(
                value = tes,
                onValueChange = {
                    tes = it
                },
                label = "Amount",
                placeholder = "Placeholder",
                modifier = Modifier
                    .fillMaxWidth()
            )

            SainggikSelectableTextField(
                value = "",
                label = "Category",
                placeholder = "Placeholder",
                trailingIcon = SainggikIcon.Dropdown,
                onTrailingIconClick = {

                }
            )

            SainggikTextAreaField(
                value = "",
                onValueChange = {

                },
                label = "Notes",
                placeholder = "Placeholder",
            )
        }
    }
}