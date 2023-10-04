package com.rubelz.eisenmatrixtodo.di

import com.rubelz.eisenmatrixtodo.feature_main.data.data_source.TaskDao
import com.rubelz.eisenmatrixtodo.feature_main.data.data_source.TaskDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideTaskDao(db: TaskDb): TaskDao{
        return db.taskDao
    }
}