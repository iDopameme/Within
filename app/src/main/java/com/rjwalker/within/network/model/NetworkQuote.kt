package com.rjwalker.within.network.model

import com.rjwalker.within.data.model.Quote
import com.rjwalker.within.database.model.QuoteEntity
import kotlinx.serialization.Serializable

@Serializable
data class NetworkQuote(
    val q: String,
    val a: String,
    val h: String,
)

fun NetworkQuote.toQuote(): Quote {
    return Quote(
        quote = q,
        author = a,
        htmlQuote = h
    )
}
