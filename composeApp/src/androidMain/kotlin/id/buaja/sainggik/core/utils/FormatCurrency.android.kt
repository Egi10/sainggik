package id.buaja.sainggik.core.utils

import java.text.NumberFormat
import java.util.Locale

actual fun formatCurrency(amount: Long, locale: AppLocale): String {
    val javaLocale = Locale(locale.languageCode, locale.countryCode)
    val formatter = NumberFormat.getCurrencyInstance(javaLocale)

    // Remove decimal places for whole numbers
    formatter.maximumFractionDigits = 0
    formatter.minimumFractionDigits = 0

    // Set specific currency if available
    try {
        val currency = java.util.Currency.getInstance(locale.currencyCode)
        formatter.currency = currency
    } catch (e: Exception) {
        // Fallback to default currency for the locale
    }

    return formatter.format(amount)
}