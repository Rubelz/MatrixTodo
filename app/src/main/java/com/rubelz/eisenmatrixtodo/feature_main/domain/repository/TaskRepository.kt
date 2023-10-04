package com.rubelz.eisenmatrixtodo.feature_main.domain.repository

import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<TaskModel>>

    suspend fun getTaskById(id: Int): TaskModel?

    suspend fun addTask(task: TaskModel)

    suspend fun deleteTask(task: TaskModel)

    suspend fun getTaskByType(task: Int): TaskModel?
}