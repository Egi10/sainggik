package id.buaja.sainggik.data.repository.transactions.mapper

import id.buaja.sainggik.core.database.entity.CategoriesTypeEntity
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.data.GetDailyTransactionSpendingByDateRange
import id.buaja.sainggik.data.GetSummaryTransactionsByDateRange
import id.buaja.sainggik.data.GetTransactionSpendingByDateRange
import id.buaja.sainggik.data.SelectAllTransactionByCategoryType
import id.buaja.sainggik.data.SelectAllTransactionById
import id.buaja.sainggik.data.Transactions
import id.buaja.sainggik.domain.model.category.TypeCategory
import id.buaja.sainggik.domain.model.transactions.TransactionGroup
import id.buaja.sainggik.domain.model.transactions.TransactionItem
import id.buaja.sainggik.domain.model.transactions.Transaction
import id.buaja.sainggik.domain.model.transactions.TransactionDailySpending
import id.buaja.sainggik.domain.model.transactions.TransactionSpending
import id.buaja.sainggik.domain.model.transactions.TransactionSummary
import id.buaja.sainggik.domain.model.transactions.TransactionsParam
import id.buaja.sainggik.domain.model.transactions.TransactionsUpdateParam

internal fun TransactionsParam.asTransactions(): Transactions {
    return Transactions(
        id = 0,
        amount = this.amount,
        category_id = this.categoryId,
        note = this.note,
        transaction_date = this.transactionDate,
        created_at = DateTimeHelper.getCurrentDateTime(),
        updated_at = DateTimeHelper.getCurrentDateTime()
    )
}

internal fun TransactionsUpdateParam.asTransactionsUpdate(): Transactions {
    return Transactions(
        id = this.id,
        amount = this.amount,
        category_id = this.categoryId,
        note = this.note,
        transaction_date = this.transactionDate,
        created_at = DateTimeHelper.getCurrentDateTime(),
        updated_at = DateTimeHelper.getCurrentDateTime()
    )
}

internal fun TypeCategory.asCategoriesTypeEntity(): CategoriesTypeEntity {
    return when (this) {
        TypeCategory.EXPENSE -> CategoriesTypeEntity.EXPENSE
        TypeCategory.INCOME -> CategoriesTypeEntity.INCOME
    }
}

internal fun SelectAllTransactionById.asTransactionDomainModel(): Transaction {
    return Transaction(
        id = this.id,
        amount = this.amount,
        categoryId = this.category_id,
        note = this.note.orEmpty(),
        transactionDate = this.transaction_date
    )
}

internal fun List<SelectAllTransactionByCategoryType>.asTransactionGroupDomainModel(): List<TransactionGroup> {
    return this.groupBy { item ->
        item.transaction_date
    }.toList().sortedByDescending { (dateLabel, _) ->
        dateLabel
    }.map { (dateLabel, items) ->
        TransactionGroup(
            dateLabel = DateTimeHelper.formatDateLabel(
                date = dateLabel,
                inputFormat = DateTimeHelper.Formats.DATE,
                outputFormat = DateTimeHelper.Formats.DATE_WITH_DAY
            ),
            items = items.map {
                TransactionItem(
                    id = it.id,
                    title = it.note ?: "(No title)",
                    category = it.category_name,
                    amount = it.amount.toCurrencyString(
                        typeEntity = it.category_type
                    )
                )
            }
        )
    }
}

internal fun GetSummaryTransactionsByDateRange.asSummaryTransactionDomainModel(): TransactionSummary {
    return TransactionSummary(
        totalExpense = this.total_expense ?: 0.0,
        totalIncome = this.total_income ?: 0.0,
        remainingBalance = this.remaining_balance ?: 0.0
    )
}

internal fun List<GetTransactionSpendingByDateRange>.asTransactionSpendingDomainModel(): List<TransactionSpending> {
    return this.map {
        TransactionSpending(
            categoryId = it.category_id,
            categoryName = it.category_name,
            totalExpense = it.total_expense,
            expensePercentage = it.expense_percentage ?: 0.0,
            totalAllExpense = it.total_all_expense
        )
    }
}

internal fun List<GetDailyTransactionSpendingByDateRange>.asTransactionDailySpendingDomainModel(): List<TransactionDailySpending> {
    return this.map {
        TransactionDailySpending(
            date = it.date,
            totalExpanses = it.total_expense
        )
    }
}

private fun Double.toCurrencyString(typeEntity: CategoriesTypeEntity): String {
    val prefix = if (typeEntity == CategoriesTypeEntity.INCOME) {
        "+Rp"
    } else {
        "-Rp"
    }
    return prefix + formatWithThousandSeparator(this)
}

private fun formatWithThousandSeparator(value: Double): String {
    val longValue = value.toLong() // buang decimal (atau kamu bisa format decimal kalau mau)
    val reversed = longValue.toString().reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
    return reversed
}
