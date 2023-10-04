package com.rubelz.eisenmatrixtodo.feature_main.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubelz.eisenmatrixtodo.feature_main.data.models.TaskDto

@Database(entities = [TaskDto::class],
    version = 1)
abstract class TaskDb: RoomDatabase() {
    abstract val taskDao: TaskDao

}

