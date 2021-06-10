package com.devM7mdibrahim.task.data.repository

import com.devM7mdibrahim.task.data.datasource.LocalDataSource
import com.devM7mdibrahim.task.data.mapper.ToDoMapper
import com.devM7mdibrahim.task.domain.model.ToDo
import com.devM7mdibrahim.task.domain.repository.ToDoRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val mapper: ToDoMapper
) : ToDoRepository {
    override fun insertToDo(toDo: ToDo): Completable {
        return localDataSource.insertToDo(mapper.mapFromDomain(toDo))
    }

    override fun deleteToDo(toDo: ToDo): Completable {
        return localDataSource.deleteToDo(mapper.mapFromDomain(toDo))
    }

    override fun updateToDo(toDo: ToDo): Completable {
        return localDataSource.updateToDo(mapper.mapFromDomain(toDo))
    }

    override fun getToDos(): Flowable<List<ToDo>> {
        return localDataSource.getToDos().map {
            it.map { todo ->
                mapper.mapToDomain(todo)
            }
        }
    }
}