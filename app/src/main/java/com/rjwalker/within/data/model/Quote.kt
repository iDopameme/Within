package com.rjwalker.within.data.model

import com.rjwalker.within.database.model.QuoteEntity

data class Quote(
    val id: Int,
    val quote: String,
    val author: String,
    val charLength: String,
    val htmlQuote: String
)

fun Quote.asEntity() = QuoteEntity(
    id = id,
    quote = quote,
    author = author,
    charLength = charLength,
    htmlQuote = htmlQuote
)

