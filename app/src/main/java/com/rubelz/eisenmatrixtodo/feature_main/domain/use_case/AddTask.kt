package com.rubelz.eisenmatrixtodo.feature_main.domain.use_case

import com.rubelz.eisenmatrixtodo.feature_main.domain.models.InvalidTaskException
import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.feature_main.domain.repository.TaskRepository
import kotlin.jvm.Throws

class AddTask(private val repository: TaskRepository) {

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: TaskModel) {
        if (task.title.isNullOrBlank()){
            throw InvalidTaskException("Title cannot be empty")
        }
        repository.addTask(task)
    }
}
