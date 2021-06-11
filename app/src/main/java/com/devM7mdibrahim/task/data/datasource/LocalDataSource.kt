package com.devM7mdibrahim.task.data.datasource

import com.devM7mdibrahim.task.data.model.TodoEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface LocalDataSource {
    fun insertTodo(todo: TodoEntity): Completable
    fun deleteTodo(todo: TodoEntity): Completable
    fun updateTodo(todo: TodoEntity): Completable
    fun getTodos(): Flowable<MutableList<TodoEntity>>
}