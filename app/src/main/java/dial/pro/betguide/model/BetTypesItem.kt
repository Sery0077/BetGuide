package dial.pro.betguide.model

import com.squareup.moshi.Json

data class BetTypesItem(
    @field:Json(name = "bet_types")
    val betTypes: Map<String, String>
)
