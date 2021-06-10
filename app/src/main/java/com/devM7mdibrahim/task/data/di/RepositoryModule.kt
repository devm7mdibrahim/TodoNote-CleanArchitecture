package com.devM7mdibrahim.task.data.di

import com.devM7mdibrahim.task.data.datasource.LocalDataSource
import com.devM7mdibrahim.task.data.mapper.ToDoMapper
import com.devM7mdibrahim.task.data.repository.ToDoRepositoryImpl
import com.devM7mdibrahim.task.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideToDoRepository(
        mapper: ToDoMapper,
        localDataSource: LocalDataSource
    ): ToDoRepository {
        return ToDoRepositoryImpl(localDataSource, mapper)
    }
}