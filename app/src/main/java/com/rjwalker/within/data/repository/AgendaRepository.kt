package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.Agenda
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {

    fun getAgendas(): Flow<List<Agenda>>

    fun getAgenda(id: Int): Flow<Agenda>

    fun getAgendaForWeek(weekStartDate: Long): Flow<List<Agenda>>

}