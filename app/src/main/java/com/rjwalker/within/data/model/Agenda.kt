package com.rjwalker.within.data.model

data class Agenda(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val isCompleted: Boolean,
    val weekStartDate: Long
)
