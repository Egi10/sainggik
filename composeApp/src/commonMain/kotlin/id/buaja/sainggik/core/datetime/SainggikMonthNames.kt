package id.buaja.sainggik.core.datetime

import kotlinx.datetime.format.MonthNames

object SainggikMonthNames {
    val INDONESIAN_ABBREVIATED: MonthNames = MonthNames(
        listOf(
            "Jan", "Feb", "Mar", "Apr", "Mei", "Jun",
            "Jul", "Agu", "Sep", "Okt", "Nov", "Des"
        )
    )
}