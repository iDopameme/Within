package com.rjwalker.within.database.di

import com.rjwalker.within.database.WithinDatabase
import com.rjwalker.within.database.dao.AgendaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesAgendaDao(
        database: WithinDatabase,
    ): AgendaDao = database.agendaDao()
}