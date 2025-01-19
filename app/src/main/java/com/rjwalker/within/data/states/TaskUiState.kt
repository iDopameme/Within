package com.rjwalker.within.data.states

import com.rjwalker.within.data.model.Task


sealed interface TaskUiState {
    data class Success(val tasks: List<Task>) : TaskUiState
    data class Error(val message: String?) : TaskUiState
    data object Loading : TaskUiState
    data object Empty : TaskUiState
}