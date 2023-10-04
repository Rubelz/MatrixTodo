package com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rubelz.eisenmatrixtodo.R
import com.rubelz.eisenmatrixtodo.feature_main.presentation.util.Screen
import com.rubelz.eisenmatrixtodo.ui.theme.iu
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import kotlinx.coroutines.launch

@Composable
fun TaskContainer(
    viewModel: TaskViewModel = hiltViewModel(),
    taskType: TaskType,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Column(
        modifier = modifier,
    ) {


        TaskTypeBanner(taskType = taskType, 0, modifier = Modifier.background(Color(taskType.color.toArgb())))

        LazyColumn {
            items(viewModel.state.value.tasks) {

                TaskItem(task = it, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.AddEditTaskScreen.route + "?taskId=${it.id}")
                    }, onDeleteClick = {

                    viewModel.onTaskEvent(TasksEvent.DeleteTask(it))
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Task Deleted", actionLabel = "Undo"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onTaskEvent(TasksEvent.RestoreTask)
                        }
                    }
                })
            }
        }
    }
}