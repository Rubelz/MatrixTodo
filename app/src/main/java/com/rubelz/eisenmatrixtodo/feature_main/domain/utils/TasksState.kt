package com.rubelz.eisenmatrixtodo.feature_main.domain.utils

import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel

data class TasksState(val tasks: List<TaskModel> = emptyList())
