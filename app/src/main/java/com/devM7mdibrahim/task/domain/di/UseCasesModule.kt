package com.devM7mdibrahim.task.domain.di

import com.devM7mdibrahim.task.domain.interactors.AddToDoUseCase
import com.devM7mdibrahim.task.domain.interactors.DeleteToDoUseCase
import com.devM7mdibrahim.task.domain.interactors.GetAllToDosUseCase
import com.devM7mdibrahim.task.domain.interactors.UpdateToDoUseCase
import com.devM7mdibrahim.task.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun provideAddToDoUseCase(repository: ToDoRepository): AddToDoUseCase = AddToDoUseCase(repository)

    @Singleton
    @Provides
    fun provideDeleteToDoUseCase(repository: ToDoRepository): DeleteToDoUseCase = DeleteToDoUseCase(repository)

    @Singleton
    @Provides
    fun provideUpdateToDoUseCase(repository: ToDoRepository): UpdateToDoUseCase = UpdateToDoUseCase(repository)

    @Singleton
    @Provides
    fun provideGetAllToDosUseCase(repository: ToDoRepository): GetAllToDosUseCase = GetAllToDosUseCase(repository)
}