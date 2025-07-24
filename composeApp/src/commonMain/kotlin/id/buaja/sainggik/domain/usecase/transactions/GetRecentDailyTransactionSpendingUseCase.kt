package id.buaja.sainggik.domain.usecase.transactions

import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.core.utils.Result
import id.buaja.sainggik.core.utils.executeWithResultStreamingFlow
import id.buaja.sainggik.domain.model.transactions.TransactionsRecentDaily
import id.buaja.sainggik.domain.repository.TransactionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus

class GetRecentDailyTransactionSpendingUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(): Flow<Result<TransactionsRecentDaily>> {
        val dateNow = DateTimeHelper.getCurrentLocalDate()
        val startDate = dateNow.minus(
            value = 7,
            unit = DateTimeUnit.DAY
        )

        return executeWithResultStreamingFlow {
            transactionsRepository.getDailyTransactionSpendingByDateRange(
                startDate = startDate.toString(),
                endDate = dateNow.toString()
            ).map {
                TransactionsRecentDaily(
                    averageSpending = if (it.isNotEmpty()) {
                        it.sumOf { dailySpending ->
                            dailySpending.totalExpanses
                        } / it.size
                    } else {
                        0.0
                    },
                    daily = it
                )
            }
        }
    }
}