package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>

    suspend fun setTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun deleteAllTasks()
}