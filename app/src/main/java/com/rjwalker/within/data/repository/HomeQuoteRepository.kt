package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.Quote
import com.rjwalker.within.data.model.asEntity
import com.rjwalker.within.database.dao.QuoteDao
import com.rjwalker.within.database.model.QuoteEntity
import com.rjwalker.within.database.model.asExternalModel
import com.rjwalker.within.network.WithinNetworkDataSource
import com.rjwalker.within.network.model.toQuote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HomeQuoteRepository @Inject constructor(
    private val quoteDao: QuoteDao,
    private val network: WithinNetworkDataSource,
): QuoteRepository {
    override fun getRandomQuote(): Flow<Quote> = flow {
        val randomNetworkQuote = network.getRandomQuote()
        val randomQuote = randomNetworkQuote.map { it.toQuote() }


        emit(randomQuote.first())
    }


    override fun getQuote(id: Int): Flow<Quote> =
        quoteDao.getQuote(id).map { it.asExternalModel() }

    override fun getQuotes(): Flow<List<Quote>> =
        quoteDao.getAllQuotes().map { it.map(QuoteEntity::asExternalModel) }


    override suspend fun deleteQuote(quote: Quote) {
        quoteDao.deleteQuote(quote.asEntity())
    }

    override suspend fun deleteAllQuotes() {
        quoteDao.deleteAllQuotes()
    }

}