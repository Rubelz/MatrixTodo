package com.rubelz.eisenmatrixtodo.feature_main.domain.use_case

import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.feature_main.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase (
    private val repository: TaskRepository
){
    operator fun invoke(): Flow<List<TaskModel>>{
        return repository.getTasks()
    }

}