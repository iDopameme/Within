package com.rjwalker.within.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rjwalker.within.data.model.Quote

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val quote: String,
    val author: String,
    val charLength: String,
    val htmlQuote: String
)

fun QuoteEntity.asExternalModel() = Quote(
    id = id,
    quote = quote,
    author = author,
    charLength = charLength,
    htmlQuote = htmlQuote
)