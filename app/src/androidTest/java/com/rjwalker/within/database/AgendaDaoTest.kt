package com.rjwalker.within.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.rjwalker.within.database.dao.AgendaDao
import com.rjwalker.within.database.model.AgendaEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class AgendaDaoTest {

    private lateinit var agendaDao: AgendaDao
    private lateinit var db: WithinDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            WithinDatabase::class.java,
        ).build()
        agendaDao = db.agendaDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun agendaDao_filter_items_by_isCompleted() = runTest {
        val agendaEntities = listOf(
            testAgendaEntity(isCompleted = true),
            testAgendaEntity(isCompleted = true),
            testAgendaEntity(isCompleted = false),
            testAgendaEntity(isCompleted = false),
            testAgendaEntity(isCompleted = false),
        )

        agendaDao.insertAllAgenda(agendaEntities)

        val savedAgendaEntities = agendaDao.getAllAgenda()

        TODO("Not yet implemented - Will Complete in future branch")

//        assertEquals(agendaEntities.size, savedAgendaEntities.size)
//
//        val completedAgendaEntities = savedAgendaEntities.filter { it.isCompleted }
//
//        assertEquals(2, completedAgendaEntities.size)
    }

}

private fun testAgendaEntity(
    id: Int = 0,
    isCompleted: Boolean = false
) = AgendaEntity(
    id = id,
    title = "title",
    description = "description",
    time = "time",
    isCompleted = isCompleted,
    weekStartDate = 0
)