package com.rjwalker.within.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjwalker.within.R
import com.rjwalker.within.design.theme.WithinTypography

//@Composable
//fun AddTaskComponent() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Start,
//        modifier = Modifier
//            .padding(4.dp)
//            .fillMaxWidth()
//    ) {
//        IconButton(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .width(40.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Rounded.Add,
//                contentDescription = stringResource(R.string.add_task),
//                modifier = Modifier
//            )
//        }
//        Text(
//            text = stringResource(R.string.add_task),
//            modifier = Modifier
//                .weight(1f)
//        )
//    }
//}
//@Composable
//fun TaskRow(
//    task: Task,
//    onCompleteTask: (Task) -> Unit = {},
//) {
//
//    var state by remember { mutableStateOf(false) }
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Start,
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        RadioButton(
//            selected = state,
//            onClick = {
//                state = !state
//                onCompleteTask(task)
//            }
//        )
//        Text(
//            text = task.title,
//        )
//    }
//}
//
//@Composable
//fun FloatingAddTask(
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit = {}
//) {
//    FloatingActionButton(
//        onClick = { onClick() },
//        modifier = modifier
//    ) {
//        Icon(Icons.Filled.Add, "Add ask button")
//    }
//}
//
//@Composable
//fun ExtendedAddTask() {
//    ExtendedFloatingActionButton(
//        onClick = { /*TODO*/ },
//        icon = { Icon(Icons.Filled.Edit, "Extended add task button") },
//        text = { Text(text = stringResource(R.string.add_task)) }
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddTaskDialog(
//    onDismiss: () -> Unit = {},
//    onAddTask: (Task) -> Unit = {},
//) {
//    var taskTitle by remember { mutableStateOf("") }
//    var taskDesc by remember { mutableStateOf("") }
//
//    BasicAlertDialog(
//        onDismissRequest = onDismiss,
//    ) {
//        Surface(
//            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
//            shape = MaterialTheme.shapes.large,
//            tonalElevation = AlertDialogDefaults.TonalElevation
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(
//                    text = stringResource(R.string.new_task),
//                    style = WithinTypography.headlineMedium
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//                TextField(
//                    value = taskTitle,
//                    onValueChange = { taskTitle = it },
//                    placeholder = {
//                        Text(text = stringResource(R.string.task_title))
//                    }
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//                TextField(
//                    value = taskDesc,
//                    onValueChange = { taskDesc = it },
//                    placeholder = {
//                        Text(text = stringResource(R.string.task_desc))
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(24.dp))
//                TextButton(
//                    onClick = { onAddTask(
//                        Task(
//                            title = taskTitle,
//                            description = taskDesc,
//                        )
//                    )
//                },
//                    modifier = Modifier.align(Alignment.End)
//                ) {
//                    Text(
//                        text = stringResource(R.string.confirm)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun AddTaskComponentPreview() {
//    AddTaskComponent()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TaskRowPreview() {
//    TaskRow(
//        task = Task(
//            title = "Go to the Gym",
//        )
//    )
//}
//
//@Preview(showBackground = false)
//@Composable
//fun FloatingAddTaskPreview() {
//    FloatingAddTask()
//}
//@Preview(showBackground = false)
//@Composable
//fun ExtendedAddTaskPreview() {
//    ExtendedAddTask()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun AddTaskDialogPreview() {
//    AddTaskDialog()
//}
