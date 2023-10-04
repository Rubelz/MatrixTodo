package com.rubelz.eisenmatrixtodo.feature_main.domain.use_case

import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.feature_main.domain.repository.TaskRepository

class GetTaskUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(id: Int): TaskModel?{
        return repository.getTaskById(id)
    }
}
