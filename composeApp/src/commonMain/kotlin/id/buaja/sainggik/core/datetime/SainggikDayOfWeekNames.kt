package id.buaja.sainggik.core.datetime

import kotlinx.datetime.format.DayOfWeekNames

object SainggikDayOfWeekNames {
    val INDONESIAN_ABBREVIATED: DayOfWeekNames = DayOfWeekNames(
        listOf(
            "Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min"
        )
    )
}