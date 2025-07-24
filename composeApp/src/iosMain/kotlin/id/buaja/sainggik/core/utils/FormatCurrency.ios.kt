package id.buaja.sainggik.core.utils

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle
import platform.Foundation.localeWithLocaleIdentifier
import platform.Foundation.numberWithLongLong

actual fun formatCurrency(amount: Long, locale: AppLocale): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterCurrencyStyle
        this.locale = NSLocale.localeWithLocaleIdentifier("${locale.languageCode}_${locale.countryCode}")
        // Remove decimal places for whole numbers
        maximumFractionDigits = 0u
        minimumFractionDigits = 0u
    }

    val nsNumber = NSNumber.numberWithLongLong(amount)
    return formatter.stringFromNumber(nsNumber) ?: getDefaultCurrencyFormat(amount, locale)
}

private fun getDefaultCurrencyFormat(amount: Long, locale: AppLocale): String {
    val symbol = when (locale) {
        AppLocale.INDONESIA -> "Rp"
        AppLocale.USA -> "$"
        AppLocale.UK -> "£"
        AppLocale.SINGAPORE -> "S$"
        AppLocale.MALAYSIA -> "RM"
        AppLocale.THAILAND -> "฿"
        AppLocale.JAPAN -> "¥"
        AppLocale.KOREA -> "₩"
        AppLocale.AUSTRALIA -> "A$"
    }
    return "$symbol ${formatNumber(amount)}"
}

private fun formatNumber(amount: Long): String {
    val amountStr = amount.toString()
    return if (amountStr.length <= 3) {
        amountStr
    } else {
        val reversed = amountStr.reversed()
        reversed.chunked(3).joinToString(",").reversed()
    }
}