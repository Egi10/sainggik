package id.buaja.sainggik.core.utils

enum class AppLocale(
    val languageCode: String,
    val countryCode: String,
    val currencyCode: String
) {
    INDONESIA("id", "ID", "IDR"),
    USA("en", "US", "USD"),
    UK("en", "GB", "GBP"),
    SINGAPORE("en", "SG", "SGD"),
    MALAYSIA("ms", "MY", "MYR"),
    THAILAND("th", "TH", "THB"),
    JAPAN("ja", "JP", "JPY"),
    KOREA("ko", "KR", "KRW"),
    AUSTRALIA("en", "AU", "AUD")
}

expect fun formatCurrency(amount: Long, locale: AppLocale): String