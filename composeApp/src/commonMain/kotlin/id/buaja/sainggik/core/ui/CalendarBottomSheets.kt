package id.buaja.sainggik.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.designsystem.components.SainggikModalBottomSheet
import id.buaja.sainggik.core.designsystem.icon.SainggikIcon
import id.buaja.sainggik.core.designsystem.icon.collection.CalendarBack
import id.buaja.sainggik.core.designsystem.icon.collection.CalendarNext
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.core.designsystem.utils.rippleClickable
import kotlinx.datetime.*
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sainggik.composeapp.generated.resources.Res
import sainggik.composeapp.generated.resources.array_day_initials

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarBottomSheets(
    onDismissRequest: () -> Unit,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
) {
    SainggikModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        content = {
            CalendarContent(
                selectedDate = selectedDate,
                onDateSelected = {
                    onDateSelected(it)
                    onDismissRequest()
                },
                minDate = minDate,
                maxDate = maxDate
            )
        }
    )
}

@Composable
private fun CalendarContent(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
) {
    var currentMonth by remember {
        mutableStateOf(selectedDate)
    }
    var selected by remember {
        mutableStateOf(selectedDate)
    }

    Column(
        modifier = modifier
    ) {
        CalendarHeader(
            currentMonth = currentMonth,
            onNextClick = {
                val nextMonth = currentMonth.plus(1, DateTimeUnit.MONTH)
                val firstOfNextMonth = LocalDate(nextMonth.year, nextMonth.month, 1)

                if (maxDate == null || firstOfNextMonth <= maxDate) {
                    currentMonth = nextMonth
                }
            },
            onPrevClick = {
                val prevMonth = currentMonth.minus(1, DateTimeUnit.MONTH)
                val lastOfPrevMonth =
                    LocalDate(prevMonth.year, prevMonth.month, getDaysInMonth(prevMonth.year, prevMonth.month))

                if (minDate == null || lastOfPrevMonth >= minDate) {
                    currentMonth = prevMonth
                }
            }
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stringArrayResource(Res.array.array_day_initials).forEach { day ->
                CalendarText(
                    modifier = Modifier
                        .weight(1f),
                    label = day,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        CalendarGrid(
            currentMonth = currentMonth,
            selectedDate = selected,
            minDate = minDate,
            maxDate = maxDate,
            onDateSelected = {
                selected = it
                onDateSelected(it)
            }
        )
    }
}

@Composable
private fun CalendarHeader(
    currentMonth: LocalDate,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 11.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CalendarButton(
            icon = SainggikIcon.CalendarBack,
            onClick = onPrevClick
        )

        Text(
            text = "${
                currentMonth.month.name.lowercase().replaceFirstChar { it.uppercase() }
            } ${currentMonth.year}",
            style = SainggikTheme.typography.body,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = SainggikTheme.colors.onBackground,
            modifier = Modifier
                .weight(1f)
        )

        CalendarButton(
            icon = SainggikIcon.CalendarNext,
            onClick = onNextClick
        )
    }
}

@Composable
private fun CalendarGrid(
    currentMonth: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null
) {
    val firstDayOfMonth = LocalDate(currentMonth.year, currentMonth.month, 1)
    val startOffset = firstDayOfMonth.dayOfWeek.isoDayNumber % 7
    val daysInMonth = getDaysInMonth(currentMonth.year, currentMonth.month)
    val totalCells = startOffset + daysInMonth
    val weeks = (totalCells + 6) / 7

    Column {
        repeat(weeks) { row ->
            CalendarWeekRow(
                row = row,
                startOffset = startOffset,
                totalCells = totalCells,
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                minDate = minDate,
                maxDate = maxDate
            )
        }
    }
}

@Composable
private fun CalendarWeekRow(
    row: Int,
    startOffset: Int,
    totalCells: Int,
    currentMonth: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (col in 0..6) {
            val index = row * 7 + col

            if (index < startOffset || index >= totalCells) {
                CalendarText(
                    label = "",
                    modifier = Modifier
                        .weight(1f)
                )
            } else {
                val day = index - startOffset + 1
                val date = LocalDate(currentMonth.year, currentMonth.month, day)
                val isSelectable = (minDate == null || date >= minDate) &&
                        (maxDate == null || date <= maxDate)

                CalendarText(
                    label = day.toString(),
                    selected = selectedDate == date,
                    enabled = isSelectable,
                    modifier = Modifier
                        .weight(1f)
                        .then(
                            other = if (isSelectable) {
                                Modifier.rippleClickable(
                                    radius = 24.dp,
                                    onClick = {
                                        onDateSelected(date)
                                    }
                                )
                            } else {
                                Modifier
                            }
                        )
                )
            }
        }
    }
}

@Composable
private fun CalendarButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .semantics {
                role = Role.Button
            }
            .size(40.dp)
            .rippleClickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = SainggikTheme.colors.onBackground
        )
    }
}

@Composable
private fun CalendarText(
    label: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    selected: Boolean = false,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .then(
                other = if (selected) {
                    Modifier.background(
                        color = SainggikTheme.colors.surfaceVariant,
                        shape = RoundedCornerShape(24.dp)
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = SainggikTheme.typography.label,
            fontWeight = fontWeight,
            color = if (enabled) {
                SainggikTheme.colors.onBackground
            } else {
                SainggikTheme.colors.onSurfaceVariant
            }
        )
    }
}


private fun getDaysInMonth(year: Int, month: Month): Int {
    return when (month) {
        Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY,
        Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> 31

        Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
        Month.FEBRUARY -> if (isLeapYear(year)) 29 else 28
    }
}

private fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

@Preview
@Composable
private fun CalendarContentPreview() {
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
            CalendarContent(
                selectedDate = DateTimeHelper.getCurrentLocalDate(),
                maxDate = LocalDate(
                    year = 2025,
                    month = 7,
                    day = 2
                ),
                onDateSelected = {

                }
            )
        }
    }
}