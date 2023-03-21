package dial.pro.betguide.data.repository

import dial.pro.betguide.data.NetworkModule
import dial.pro.betguide.model.BetStrategyItem
import dial.pro.betguide.model.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BetStrategyRepository {
    fun getStrategy(): Flow<DataResult<BetStrategyItem>> {
        return flow {
            emit(DataResult.loading())

            val result = try {
                val data = NetworkModule.apiService.getStrategyList()
                DataResult.success(data)
            } catch (e: Exception) {
                DataResult.error(e.message)
            }

            emit(result)
        }
    }
}