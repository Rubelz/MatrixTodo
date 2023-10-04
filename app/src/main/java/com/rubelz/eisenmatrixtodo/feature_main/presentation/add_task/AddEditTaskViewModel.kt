package com.rubelz.eisenmatrixtodo.feature_main.presentation.add_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubelz.eisenmatrixtodo.feature_main.domain.models.InvalidTaskException
import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.feature_main.domain.use_case.TaskInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currTaskId: Int? = null
    private var currTaskType: Int = -1

    private val _taskTitle = mutableStateOf(
        TaskTextFieldState(
            hint = "Title"
        )
    )
    val taskTitle: State<TaskTextFieldState> = _taskTitle

    private val _taskContent = mutableStateOf(
        TaskTextFieldState(
            hint = "Task Content"
        )
    )
    val taskContent: State<TaskTextFieldState> = _taskContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    taskInteractor.getTask(taskId)?.also {
                        currTaskId = it.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = it.title,
                            isHintVisible = false
                        )
                        _taskContent.value = taskContent.value.copy(
                            text = it.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(text = event.value)
            }

            is AddEditTaskEvent.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            taskTitle.value.text.isNullOrBlank()
                )
            }

            is AddEditTaskEvent.EnteredContent -> {
                _taskContent.value = taskContent.value.copy(text = event.value)
            }

            is AddEditTaskEvent.ChangeContentFocus -> {
                _taskContent.value = taskContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            taskContent.value.text.isNullOrBlank()
                )
            }

            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskInteractor.addTask(
                            TaskModel(
                                title = taskTitle.value.text,
                                content = taskContent.value.text,
                                id = currTaskId,
                                taskType =currTaskType
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save task"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveTask : UiEvent()
    }
}