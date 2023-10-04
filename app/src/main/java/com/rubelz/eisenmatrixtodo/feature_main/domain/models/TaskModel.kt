package com.rubelz.eisenmatrixtodo.feature_main.domain.models


data class TaskModel(
    val id: Int? = null,
    val title: String,
    val content: String,
    val taskType: Int,
)

class InvalidTaskException(msg: String): Exception(msg)