package com.rjwalker.within.feature.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rjwalker.within.R
import com.rjwalker.within.data.model.Task
import com.rjwalker.within.data.states.TaskUiState
import com.rjwalker.within.design.components.AddTaskDialog
import com.rjwalker.within.design.components.FloatingAddTask
import com.rjwalker.within.design.components.TaskRow
import com.rjwalker.within.design.components.ThemePreviews
import com.rjwalker.within.design.theme.WithinTypography

@Composable
internal fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksScreenViewModel = hiltViewModel(),
) {
    val tasks = viewModel.userTasks.collectAsStateWithLifecycle().value
    val showDialog = viewModel.showDialog.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (tasks) {
            TaskUiState.Loading -> {
                CircularProgressIndicator()
            }
            TaskUiState.Empty -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.no_tasks),
                        style = WithinTypography.headlineMedium,
                    )
                    Text(
                        text = stringResource(R.string.no_tasks_desc),
                        style = WithinTypography.bodyMedium
                    )
                }
                FloatingAddTask(modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
                )
            }
            is TaskUiState.Success -> {
                TasksList(tasks = tasks.tasks)

                FloatingAddTask(modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                    onClick = { viewModel.onShowTaskDialog() }
                )

                if (showDialog) {
                    AddTaskDialog(
                        onDismiss = { viewModel.onTaskDialogDismiss() },
                        onAddTask = { viewModel.setTask(it) }
                    )
                }
            }
            is TaskUiState.Error -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.tasks_load_error, tasks.message.toString()),
                        style = WithinTypography.titleSmall
                    )
                }
            }
        }


    }

}

@Composable
fun TasksList(tasks: List<Task>) {
    Column {
        tasks.forEach { task ->
            TaskRow(
                task
            )
        }
    }
}


@ThemePreviews
@Composable
fun TasksScreenPreview() {
    TasksScreen()
}