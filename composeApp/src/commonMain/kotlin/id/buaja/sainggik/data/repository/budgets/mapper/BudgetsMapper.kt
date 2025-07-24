package id.buaja.sainggik.data.repository.budgets.mapper

import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.data.Budgets
import id.buaja.sainggik.data.GetBudgetsExceedingThreshold
import id.buaja.sainggik.data.SelectAllBudgetsWithCategory
import id.buaja.sainggik.data.SelectBudgetSummary
import id.buaja.sainggik.domain.model.budgets.BudgetCategory
import id.buaja.sainggik.domain.model.budgets.BudgetGroup
import id.buaja.sainggik.domain.model.budgets.BudgetSummaryCategory
import id.buaja.sainggik.domain.model.budgets.BudgetSummaryGroup
import id.buaja.sainggik.domain.model.budgets.BudgetUpdateParam
import id.buaja.sainggik.domain.model.budgets.Budget
import id.buaja.sainggik.domain.model.budgets.BudgetExceeding
import id.buaja.sainggik.domain.model.budgets.BudgetsParam
import kotlin.math.roundToInt

internal fun BudgetsParam.asBudgets(): Budgets {
    return Budgets(
        id = 0,
        category_id = this.categoryId,
        start_date = this.startDate,
        end_date = this.endDate,
        amount_limit = this.amountLimit,
        created_at = DateTimeHelper.getCurrentDateTime(),
        updated_at = DateTimeHelper.getCurrentDateTime()
    )
}

internal fun List<SelectAllBudgetsWithCategory>.asBudgetsGroupDomainModel(): List<BudgetGroup> {
    return this.groupBy { item ->
        "${item.start_date} - ${item.end_date}"
    }.map { (period, items) ->
        val (startDate, endDate) = period.split(" - ")

        val (formattedStart, formattedEnd) = formatPeriodDates(startDate, endDate)

        BudgetGroup(
            startDate = formattedStart,
            endDate = formattedEnd,
            rawStartDate = startDate,
            categories = items.map {
                BudgetCategory(
                    budgetId = it.id,
                    categoryId = it.category_id,
                    name = it.category_name,
                    budgetAmount = it.amount_limit
                )
            }
        )
    }
}

internal fun List<SelectBudgetSummary>.asBudgetsSummaryGroupDomainModel(): List<BudgetSummaryGroup> {
    return this.groupBy { item ->
        "${item.start_date} - ${item.end_date}"
    }.map { (period, items) ->
        val (startDate, endDate) = period.split(" - ")

        val (formattedStart, formattedEnd) = formatPeriodDates(startDate, endDate)

        BudgetSummaryGroup(
            startDate = formattedStart,
            endDate = formattedEnd,
            rawStartDate = startDate,
            categories = items.map {
                BudgetSummaryCategory(
                    budgetId = it.budget_id,
                    categoryId = it.category_id,
                    name = it.category_name,
                    budgetAmount = it.budget_amount,
                    remainingAmount = it.remaining_amount,
                    spentAmount = it.spent_amount,
                    progress = if (it.budget_amount > 0) {
                        ((it.spent_amount / it.budget_amount) * 100).toInt()
                    } else {
                        0
                    }
                )
            }
        )
    }.sortedByDescending {
        it.rawStartDate
    }
}

internal fun Budgets.asBudgetDomainModel(): Budget {
    return Budget(
        id = this.id,
        categoryId = this.category_id,
        startDate = this.start_date,
        endDate = this.end_date,
        amountLimit = this.amount_limit,
        createdAt = this.created_at
    )
}

internal fun BudgetUpdateParam.asUpdateBudgets(): Budgets {
    return Budgets(
        id = this.id,
        start_date = this.startDate,
        end_date = this.endDate,
        amount_limit = this.amountLimit,
        updated_at = DateTimeHelper.getCurrentDateTime(),
        created_at = DateTimeHelper.getCurrentDateTime(),
        category_id = 0
    )
}

internal fun List<GetBudgetsExceedingThreshold>.asBudgetExceedingDomainModel(): List<BudgetExceeding> {
    return this.map {
        BudgetExceeding(
            budgetId = it.budget_id,
            categoryName = it.category_name,
            startDate = it.start_date,
            endDate = it.end_date,
            amountLimit = it.amount_limit,
            totalExpense = it.total_expense,
            expensePercentage = it.expense_percentage ?: 0.0
        )
    }
}

private fun formatPeriodDates(
    startDate: String,
    endDate: String
): Pair<String, String> {
    val yearStartDate = DateTimeHelper.formatDate(
        date = startDate,
        inputFormat = DateTimeHelper.Formats.DATE,
        outputFormat = DateTimeHelper.Formats.YEAR
    )
    val yearEndDate = DateTimeHelper.formatDate(
        date = endDate,
        inputFormat = DateTimeHelper.Formats.DATE,
        outputFormat = DateTimeHelper.Formats.YEAR
    )

    val startOutputFormat = if (yearStartDate == yearEndDate) {
        DateTimeHelper.Formats.DAY_MONT
    } else {
        DateTimeHelper.Formats.DATE_SHORT
    }

    val formattedStartDate = DateTimeHelper.formatDate(
        date = startDate,
        inputFormat = DateTimeHelper.Formats.DATE,
        outputFormat = startOutputFormat
    )

    val formattedEndDate = DateTimeHelper.formatDate(
        date = endDate,
        inputFormat = DateTimeHelper.Formats.DATE,
        outputFormat = DateTimeHelper.Formats.DATE_SHORT
    )

    return formattedStartDate to formattedEndDate
}