package com.devM7mdibrahim.task.domain.di

import com.devM7mdibrahim.task.domain.interactors.AddTodoUseCase
import com.devM7mdibrahim.task.domain.interactors.DeleteTodoUseCase
import com.devM7mdibrahim.task.domain.interactors.GetAllTodosUseCase
import com.devM7mdibrahim.task.domain.interactors.UpdateTodoUseCase
import com.devM7mdibrahim.task.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @ViewModelScoped
    @Provides
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodoUseCase = AddTodoUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase = DeleteTodoUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase = UpdateTodoUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetAllTodosUseCase(repository: TodoRepository): GetAllTodosUseCase = GetAllTodosUseCase(repository)
}