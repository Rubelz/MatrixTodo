package com.rubelz.eisenmatrixtodo.feature_main.presentation.add_task

data class TaskTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)
