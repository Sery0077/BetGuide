package dial.pro.betguide.model

import com.squareup.moshi.Json

data class FAQItem(
    @field:Json(name = "faq")
    val text: String
)
