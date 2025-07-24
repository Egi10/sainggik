package id.buaja.sainggik.core.ui.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import id.buaja.sainggik.core.utils.AppLocale
import id.buaja.sainggik.core.utils.formatCurrency

class CurrencyVisualTransformation(
    private val locale: AppLocale = AppLocale.INDONESIA
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text.filter { it.isDigit() }
        val formatted = if (rawText.isEmpty()) {
            "" // Empty if user deletes all
        } else {
            val numericValue = rawText.toLongOrNull() ?: 0L
            if (numericValue == 0L) {
                ""
            } else {
                formatCurrency(numericValue, locale)
            }
        }

        return TransformedText(
            text = AnnotatedString(formatted),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (formatted.isEmpty()) {
                        return 0
                    }
                    val extraChars = formatted.length - rawText.length
                    return (offset + extraChars).coerceIn(0, formatted.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (formatted.isEmpty()) {
                        return 0
                    }
                    val extraChars = formatted.length - rawText.length
                    return (offset - extraChars).coerceAtLeast(0)
                }
            }
        )
    }
}
