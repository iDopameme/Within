package com.rjwalker.within.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rjwalker.within.database.model.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert
    suspend fun insertQuote(quote: QuoteEntity)

    @Query(value = "SELECT * FROM quotes where id = :id")
    fun getQuote(id: Int): Flow<QuoteEntity>

    @Query(value = "SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Delete
    suspend fun deleteQuote(quote: QuoteEntity)

    @Query(value = "DELETE FROM quotes")
    suspend fun deleteAllQuotes()
}