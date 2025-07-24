package id.buaja.sainggik.core.datetime

import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.char
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object DateTimeHelper {
    @OptIn(FormatStringsInDatetimeFormats::class, ExperimentalTime::class)
    fun getCurrentDateTime(
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String {
        val dateTimeFormat = LocalDateTime.Format {
            byUnicodePattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        }
        val currentMoment: Instant = Clock.System.now()

        val formatted = currentMoment.toLocalDateTime(timeZone = timeZone)
        return formatted.format(dateTimeFormat)
    }

    @OptIn(ExperimentalTime::class)
    fun getCurrentLocalDate(
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): LocalDate {
        val currentMoment: Instant = Clock.System.now()
        return currentMoment.toLocalDateTime(
            timeZone = timeZone
        ).date
    }

    fun parseToLocalDate(
        date: String,
        format: DateTimeFormat<LocalDate>,
    ): LocalDate {
        return format.parse(
            input = date
        )
    }

    fun formatDate(
        date: String,
        inputFormat: DateTimeFormat<LocalDate>,
        outputFormat: DateTimeFormat<LocalDate>
    ): String {
        val parsedDate = inputFormat.parse(
            input = date
        )
        return parsedDate.format(outputFormat)
    }

    fun formatDate(
        date: LocalDate,
        outputFormat: DateTimeFormat<LocalDate>
    ): String {
        return date.format(outputFormat)
    }

    fun formatDateLabel(
        date: String,
        inputFormat: DateTimeFormat<LocalDate>,
        outputFormat: DateTimeFormat<LocalDate>
    ): String {
        val parsedDate = inputFormat.parse(
            input = date
        )
        val today = getCurrentLocalDate()
        val yesterday = today.minus(
            value = 1,
            unit = DateTimeUnit.DAY
        )

        return when (parsedDate) {
            today -> {
                "Hari Ini"
            }

            yesterday -> {
                "Kemarin"
            }

            else -> {
                formatDate(
                    date = date,
                    inputFormat = inputFormat,
                    outputFormat = outputFormat
                )
            }
        }
    }

    object Formats {
        val DATE: DateTimeFormat<LocalDate> = LocalDate.Format {
            year()
            char('-')
            monthNumber()
            char('-')
            day()
        }

        val DATE_SHORT: DateTimeFormat<LocalDate> = LocalDate.Format {
            day()
            char(' ')
            monthName(
                names = SainggikMonthNames.INDONESIAN_ABBREVIATED
            )
            char(' ')
            year()
        }

        val DATE_WITH_DAY: DateTimeFormat<LocalDate> = LocalDate.Format {
            dayOfWeek(
                names = SainggikDayOfWeekNames.INDONESIAN_ABBREVIATED
            )
            char(',')
            char(' ')
            day()
            char(' ')
            monthName(
                names = SainggikMonthNames.INDONESIAN_ABBREVIATED
            )
            char(' ')
            year()
        }

        val YEAR: DateTimeFormat<LocalDate> = LocalDate.Format {
            year()
        }

        val DAY_MONT: DateTimeFormat<LocalDate> = LocalDate.Format {
            day()
            char(' ')
            monthName(
                names = SainggikMonthNames.INDONESIAN_ABBREVIATED
            )
        }

        val DAY_WEEK: DateTimeFormat<LocalDate> = LocalDate.Format {
            dayOfWeek(
                names = SainggikDayOfWeekNames.INDONESIAN_ABBREVIATED
            )
        }
    }
}