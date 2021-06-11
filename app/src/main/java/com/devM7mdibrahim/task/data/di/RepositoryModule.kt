package com.devM7mdibrahim.task.data.di

import com.devM7mdibrahim.task.data.datasource.LocalDataSource
import com.devM7mdibrahim.task.data.mapper.TodoMapper
import com.devM7mdibrahim.task.data.repository.TodoRepositoryImpl
import com.devM7mdibrahim.task.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTodoRepository(
        mapper: TodoMapper,
        localDataSource: LocalDataSource
    ): TodoRepository {
        return TodoRepositoryImpl(localDataSource, mapper)
    }
}