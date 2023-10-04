package com.rubelz.eisenmatrixtodo.feature_main.data.repository

import com.rubelz.eisenmatrixtodo.core.ext.toDomain
import com.rubelz.eisenmatrixtodo.core.ext.toDto
import com.rubelz.eisenmatrixtodo.feature_main.data.data_source.TaskDao
import com.rubelz.eisenmatrixtodo.feature_main.data.models.TaskDto
import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.feature_main.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {
    override fun getTasks(): Flow<List<TaskModel>> {
        val tasks: Flow<List<TaskModel>> = dao.getTasks().map { list ->
            list.map { dto ->
                dto.toDomain()
            }
        }
        return tasks
    }

    override suspend fun getTaskById(id: Int): TaskModel? {
        val task = dao.getTaskById(id)?.toDomain()
        return task
    }

    override suspend fun addTask(task: TaskModel) {
        val newTask = task.toDto()
        dao.addTask(newTask)
    }

    override suspend fun deleteTask(task: TaskModel) {
        val newTask = task.toDto()
        dao.deleteTask(newTask)
    }

    override suspend fun getTaskByType(type: Int): TaskModel?{
        val filteredTask = dao.getTaskByType(type)?.toDomain()
        return filteredTask

    }
}