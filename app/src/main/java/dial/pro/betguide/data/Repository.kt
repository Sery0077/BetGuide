package dial.pro.betguide.data

import dial.pro.betguide.model.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KFunction

object Repository {
    fun <T> performRequest(request: suspend ()-> T): Flow<DataResult<T>> {
        return flow {
            emit(DataResult.loading())

            val result = try {
                val data = request.invoke()
                DataResult.success(data)
            } catch (e: Exception) {
                DataResult.error(e.localizedMessage)
            }

            emit(result)
        }
    }
}