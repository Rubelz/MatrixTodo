package com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.feature_main.domain.use_case.TaskInteractor
import com.rubelz.eisenmatrixtodo.feature_main.domain.utils.TasksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var deletedTask: TaskModel? = null

    private var getTasksJob: Job? = null

    init {
        getTasks()
    }

    fun onTaskEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskInteractor.deleteTask(event.task)
                    deletedTask = event.task
                }
            }

            is TasksEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskInteractor.addTask.invoke(deletedTask ?: return@launch)
                    deletedTask = null
                }
            }
        }
    }

    private fun getTasks() {
        getTasksJob?.cancel()
        taskInteractor.getTasks()
            .onEach { tasks ->
                _state.value = state.value.copy(tasks)
            }
            .launchIn(viewModelScope)
    }
}
