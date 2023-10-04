package com.rubelz.eisenmatrixtodo.core.ext

import com.rubelz.eisenmatrixtodo.feature_main.data.models.TaskDto
import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun TaskDto.toDomain() = TaskModel(
    id = this.id,
    title = this.title,
    content = this.content,
    taskType = this.taskType
)

fun TaskModel.toDto() = TaskDto(
    id = this.id,
    title = this.title,
    content = this.content,
    taskType = this.taskType
)

