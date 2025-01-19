package com.rjwalker.within.data.model

import com.rjwalker.within.database.model.TaskEntity


data class Task(
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val priority: Int? = null,
)

fun Task.asEntity() = TaskEntity(
    title = title,
    description = description,
    isCompleted = isCompleted,
    priority = priority
)
