package com.rjwalker.within.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rjwalker.within.database.dao.AgendaDao
import com.rjwalker.within.database.model.AgendaEntity

@Database(entities = [
    AgendaEntity::class
    ],
    version = 1
)

internal abstract class WithinDatabase : RoomDatabase() {
    abstract fun agendaDao(): AgendaDao
}