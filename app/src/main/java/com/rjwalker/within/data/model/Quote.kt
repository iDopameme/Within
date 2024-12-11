package com.rjwalker.within.data.model

import com.rjwalker.within.database.model.QuoteEntity

data class Quote(
    val quote: String,
    val author: String,
    val htmlQuote: String
)

fun Quote.asEntity() = QuoteEntity(
    quote = quote,
    author = author,
    htmlQuote = htmlQuote
)

