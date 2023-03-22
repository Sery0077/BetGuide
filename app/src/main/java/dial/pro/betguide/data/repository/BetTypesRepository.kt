package dial.pro.betguide.data.repository

import dial.pro.betguide.data.NetworkModule
import dial.pro.betguide.model.BetTypesItem
import dial.pro.betguide.model.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BetTypesRepository {
    fun getBetTypes(): Flow<DataResult<BetTypesItem>> {
        return flow {
            emit(DataResult.loading())

            val result = try {
                val data = NetworkModule.apiService.getBetTypesList()
                DataResult.success(data)
            } catch (e: Exception) {
                DataResult.error(e.localizedMessage)
            }

            emit(result)
        }
    }
}