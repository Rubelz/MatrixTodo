package com.rubelz.eisenmatrixtodo.feature_main.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rubelz.eisenmatrixtodo.feature_main.data.models.TaskDto

import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("Select * From task")
    fun getTasks(): Flow<List<TaskDto>>

    @Query("Select * From task Where id=:id")
    suspend fun getTaskById(id: Int): TaskDto?

    @Query("Select * From task Where  taskType=:taskType")
    suspend fun getTaskByType(taskType: Int): TaskDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskDto)

    @Delete
    suspend fun deleteTask(task: TaskDto)
}
