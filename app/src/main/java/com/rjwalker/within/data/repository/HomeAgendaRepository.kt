package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.Agenda
import com.rjwalker.within.database.dao.AgendaDao
import com.rjwalker.within.database.model.AgendaEntity
import com.rjwalker.within.database.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeAgendaRepository @Inject constructor(
    private val agendaDao: AgendaDao
) : AgendaRepository {
    override fun getAgendas(): Flow<List<Agenda>> =
        agendaDao.getAllAgenda()
            .map { it.map(AgendaEntity::asExternalModel) }


    override fun getAgenda(id: Int): Flow<Agenda> =
        agendaDao.getAgenda(id).map { it.asExternalModel() }

    override fun getAgendaForWeek(weekStartDate: Long): Flow<List<Agenda>> {
        TODO("Not yet implemented")
    }


}