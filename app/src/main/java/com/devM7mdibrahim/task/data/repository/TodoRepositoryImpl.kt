package com.devM7mdibrahim.task.data.repository

import com.devM7mdibrahim.task.data.datasource.LocalDataSource
import com.devM7mdibrahim.task.data.mapper.TodoMapper
import com.devM7mdibrahim.task.domain.model.TodoModel
import com.devM7mdibrahim.task.domain.repository.TodoRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val mapper: TodoMapper
) : TodoRepository {
    override fun insertTodo(todo: TodoModel): Completable {
        return localDataSource.insertTodo(mapper.mapFromDomain(todo))
    }

    override fun deleteTodo(todo: TodoModel): Completable {
        return localDataSource.deleteTodo(mapper.mapFromDomain(todo))
    }

    override fun updateTodo(todo: TodoModel): Completable {
        return localDataSource.updateTodo(mapper.mapFromDomain(todo))
    }

    override fun getTodos(): Flowable<MutableList<TodoModel>> {
        return localDataSource.getTodos().map {
            it.map { todo ->
                mapper.mapToDomain(todo)
            }.toMutableList()
        }
    }
}