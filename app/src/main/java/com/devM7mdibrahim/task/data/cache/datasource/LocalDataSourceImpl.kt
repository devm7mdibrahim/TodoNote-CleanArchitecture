package com.devM7mdibrahim.task.data.cache.datasource

import com.devM7mdibrahim.task.data.cache.dao.TodoDao
import com.devM7mdibrahim.task.data.cache.mapper.CachedTodoMapper
import com.devM7mdibrahim.task.data.datasource.LocalDataSource
import com.devM7mdibrahim.task.data.model.TodoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: TodoDao,
    private val mapper: CachedTodoMapper
) : LocalDataSource {
    override fun insertTodo(todo: TodoEntity): Completable =
        dao.insertTodo(mapper.mapToCached(todo))

    override fun deleteTodo(todo: TodoEntity) = dao.delete(mapper.mapToCached(todo))

    override fun updateTodo(todo: TodoEntity) = dao.updateToDO(mapper.mapToCached(todo))

    override fun getTodos(): Flowable<MutableList<TodoEntity>> = dao.getAllToDOs().map {
        it.map { todo ->
            mapper.mapFromCached(todo)
        }.toMutableList()
    }
}