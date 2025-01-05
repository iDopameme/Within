package com.rjwalker.within.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "tasks")
//data class TaskEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    @ColumnInfo(name = "title") val title: String,
//    @ColumnInfo(name = "description") val description: String = "",
//    @ColumnInfo(name = "isCompleted") val isCompleted: Boolean = false,
//    @ColumnInfo(name = "priority") val priority: Int? = 0,
//)
//
//fun TaskEntity.asExternalModel() = Task(
//    title = title,
//    description = description,
//    isCompleted = isCompleted,
//    priority = priority
//)