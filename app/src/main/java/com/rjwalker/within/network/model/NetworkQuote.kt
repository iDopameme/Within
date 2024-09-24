package com.rjwalker.within.network.model

import android.os.Parcelable
import com.rjwalker.within.database.model.QuoteEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkQuote(
    val quote: String,
    val author: String,
    val charLength: String,
    val htmlQuote: String,
) : Parcelable

fun NetworkQuote.asEntity() = QuoteEntity(
    quote = quote,
    author = author,
    charLength = charLength,
    htmlQuote = htmlQuote
)