package dial.pro.betguide.model.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dial.pro.betguide.model.BetStrategyItem
import okio.IOException

object JsonUtils {
    fun fromJson(json: String): BetStrategyItem {
        val moshiAdapter = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Map<String, String>> = moshiAdapter.adapter(
            Types.newParameterizedType(
                Map::class.java,
                String::class.java,
                String::class.java
            )
        )

        val map = jsonAdapter.fromJson(json) ?: throw IOException("fromJson exception")
        return BetStrategyItem(map)
    }
}