package com.rjwalker.within.feature.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjwalker.within.data.model.Task
import com.rjwalker.within.data.repository.TaskRepository
import com.rjwalker.within.data.repository.UserDataRepository
import com.rjwalker.within.data.states.TaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
): ViewModel() {

    private val _userTasks = MutableStateFlow<TaskUiState>(TaskUiState.Loading)
    val userTasks: StateFlow<TaskUiState> = _userTasks.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    init {
        observeTasks()
    }

    private fun observeTasks() {
        viewModelScope.launch {
            taskRepository.getTasks()
                .catch { exception ->
                    _userTasks.value = TaskUiState.Error(exception.message)
                    Log.d(TAG, "Error fetching tasks", exception)
                }
                .collect { tasks ->
                    if (tasks.isEmpty()) {
                        _userTasks.value = TaskUiState.Empty
                    } else {
                        _userTasks.value = TaskUiState.Success(tasks)
                    }
                }
        }
    }

    fun onShowTaskDialog() {
        _showDialog.value = true
    }

    fun onTaskDialogDismiss() {
        _showDialog.value = false
    }

    fun setTask(task: Task) {
        viewModelScope.launch {
            taskRepository.setTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            taskRepository.deleteAllTasks()
        }
    }

    companion object {
        private const val TAG = "TasksScreenViewModel"
    }

}