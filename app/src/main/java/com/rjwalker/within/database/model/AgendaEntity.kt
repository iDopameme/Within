package com.rjwalker.within.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rjwalker.within.data.model.Agenda

@Entity(
    tableName = "agendas"
)
data class AgendaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val time: String,
    val isCompleted: Boolean,
    val weekStartDate: Long
)

fun AgendaEntity.asExternalModel() = Agenda(
    id = id,
    title = title,
    description = description,
    time = time,
    isCompleted = isCompleted,
    weekStartDate = weekStartDate
)
