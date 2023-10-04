package com.rubelz.eisenmatrixtodo.feature_main.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskDto (
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val taskType: Int,
)