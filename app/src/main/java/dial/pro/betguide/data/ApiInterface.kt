package dial.pro.betguide.data

import dial.pro.betguide.model.BetStrategyItem
import retrofit2.http.GET

interface ApiInterface {
    @GET("data/bet_strategy.json")
    suspend fun getStrategyList(): BetStrategyItem
}