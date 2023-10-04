package com.rubelz.eisenmatrixtodo.di

import android.app.Application
import androidx.room.Room
import com.rubelz.eisenmatrixtodo.feature_main.data.data_source.TaskDb
import com.rubelz.eisenmatrixtodo.feature_main.data.repository.TaskRepositoryImpl
import com.rubelz.eisenmatrixtodo.feature_main.domain.repository.TaskRepository
import com.rubelz.eisenmatrixtodo.feature_main.domain.use_case.AddTask
import com.rubelz.eisenmatrixtodo.feature_main.domain.use_case.DeleteTaskUseCase
import com.rubelz.eisenmatrixtodo.feature_main.domain.use_case.GetTaskUseCase
import com.rubelz.eisenmatrixtodo.feature_main.domain.use_case.GetTasksUseCase
import com.rubelz.eisenmatrixtodo.feature_main.domain.use_case.TaskInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(app: Application): TaskDb {
        return Room.databaseBuilder(
            app,
            TaskDb::class.java, "task_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepo(db: TaskDb): TaskRepository{
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskInteractor(repository: TaskRepositoryImpl): TaskInteractor{
        return TaskInteractor(
            GetTasksUseCase(repository),
            DeleteTaskUseCase(repository),
            AddTask(repository),
            GetTaskUseCase(repository)
        )
    }
}