package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.Task
import com.rjwalker.within.data.model.asEntity
import com.rjwalker.within.database.dao.TaskDao
import com.rjwalker.within.database.model.TaskEntity
import com.rjwalker.within.database.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TaskHomeRepository @Inject constructor(
    private val taskDao: TaskDao,
): TaskRepository {
    override fun getTasks(): Flow<List<Task>> =
        taskDao.getAllTasks().map { it.map(TaskEntity::asExternalModel) }

    override suspend fun setTask(task: Task) {
        taskDao.insertTask(task.asEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.asEntity())
    }

    override suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

}