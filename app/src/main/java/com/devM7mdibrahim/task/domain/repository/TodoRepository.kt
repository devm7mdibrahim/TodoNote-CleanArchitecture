package com.devM7mdibrahim.task.domain.repository

import com.devM7mdibrahim.task.domain.model.TodoModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface TodoRepository {
    fun insertTodo(todo: TodoModel): Completable
    fun deleteTodo(todo: TodoModel): Completable
    fun updateTodo(todo: TodoModel): Completable
    fun getTodos(): Flowable<MutableList<TodoModel>>
}