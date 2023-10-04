package com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components

import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel

sealed class TasksEvent {
    data class DeleteTask(val task: TaskModel): TasksEvent()
    object RestoreTask: TasksEvent()
}