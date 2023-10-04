package com.rubelz.eisenmatrixtodo.feature_main.domain.use_case

import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.feature_main.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: TaskModel) {
        repository.deleteTask(task)
    }
}