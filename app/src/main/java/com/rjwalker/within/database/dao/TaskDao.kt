package com.rjwalker.within.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rjwalker.within.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query(value = "SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query(value = "DELETE FROM tasks")
    suspend fun deleteAllTasks()
}