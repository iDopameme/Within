package com.rjwalker.within.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AgendaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val time: String,
    val isCompleted: Boolean,
    val weekStartDate: Long
)
