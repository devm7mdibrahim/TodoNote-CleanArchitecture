package com.devM7mdibrahim.task.data.cache.datasource

import com.devM7mdibrahim.task.data.cache.dao.ToDoDao
import com.devM7mdibrahim.task.data.cache.mapper.CachedToDoMapper
import com.devM7mdibrahim.task.data.datasource.LocalDataSource
import com.devM7mdibrahim.task.data.model.ToDoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: ToDoDao,
    private val mapper: CachedToDoMapper
) : LocalDataSource {
    override fun insertToDo(todo: ToDoEntity): Completable = dao.insertTodo(mapper.mapToCached(todo))

    override fun deleteToDo(todo: ToDoEntity) = dao.delete(mapper.mapToCached(todo))

    override fun updateToDo(todo: ToDoEntity) = dao.updateToDO(mapper.mapToCached(todo))

    override fun getToDos(): Flowable<List<ToDoEntity>> = dao.getAllToDOs().map {
        it.map { todo ->
            mapper.mapFromCached(todo)
        }
    }
}