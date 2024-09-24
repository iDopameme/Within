package com.rjwalker.within.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.rjwalker.within.database.dao.AgendaDao
import com.rjwalker.within.database.dao.QuoteDao
import com.rjwalker.within.database.model.AgendaEntity
import com.rjwalker.within.database.model.QuoteEntity

@Database(entities = [
    AgendaEntity::class,
    QuoteEntity::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true
)

internal abstract class WithinDatabase : RoomDatabase() {
    abstract fun agendaDao(): AgendaDao
    abstract fun quoteDao(): QuoteDao
}