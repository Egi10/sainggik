package id.buaja.sainggik.core.designsystem.utils

fun String.initials(): String {
    return this
        .trim()
        .split("\\s+".toRegex())           // Split by whitespace
        .filter { it.isNotEmpty() && it.first().isLetter() } // Skip simbol
        .take(2)                            // Ambil maksimal 2 kata
        .map { it.first().uppercaseChar() } // Ambil huruf pertama uppercase
        .joinToString("")
}