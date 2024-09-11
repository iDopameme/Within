package com.rjwalker.within.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rjwalker.within.database.model.AgendaEntity

@Dao
interface AgendaDao {
    @Insert
    suspend fun insertAgenda(agenda: AgendaEntity)

    @Query("SELECT * FROM AgendaEntity WHERE weekStartDate = :weekStartDate")
    suspend fun getAgendaForWeek(weekStartDate: Long): List<AgendaEntity>

    @Query("SELECT * FROM AgendaEntity")
    suspend fun getAllAgenda(): List<AgendaEntity>

}