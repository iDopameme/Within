package com.rjwalker.within.database.di

import android.content.Context
import androidx.room.Room
import com.rjwalker.within.database.WithinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesWithinDatabase(
        @ApplicationContext context: Context,
    ): WithinDatabase = Room.databaseBuilder(
        context,
        WithinDatabase::class.java,
        "within-database"
    ).build()
}
