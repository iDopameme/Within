package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuote(id: Int): Flow<Quote>

    fun getQuotes(): Flow<List<Quote>>

    suspend fun deleteQuote(quote: Quote)

    suspend fun deleteAllQuotes()
}