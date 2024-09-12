package com.rjwalker.within.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rjwalker.within.database.model.AgendaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDao {
    @Insert
    suspend fun insertAgenda(agenda: AgendaEntity)

    @Insert
    suspend fun insertAllAgenda(agenda: List<AgendaEntity>)

    @Query(value = "SELECT * FROM agendas WHERE weekStartDate = :weekStartDate")
    fun getAgendaForWeek(weekStartDate: Long): List<AgendaEntity>

    @Query(value = "SELECT * FROM agendas WHERE id = :id")
    fun getAgenda(id: Int): Flow<AgendaEntity>

    @Query(value = "SELECT * FROM agendas")
    fun getAllAgenda(): Flow<List<AgendaEntity>>

    @Delete
    suspend fun deleteAgenda(agenda: AgendaEntity)
}