package com.rubelz.eisenmatrixtodo.feature_main.domain.use_case

data class TaskInteractor(val getTasks: GetTasksUseCase,
    val deleteTask: DeleteTaskUseCase, val addTask: AddTask, val getTask: GetTaskUseCase) {
}