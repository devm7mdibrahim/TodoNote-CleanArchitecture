package com.devM7mdibrahim.task.data.datasource

import com.devM7mdibrahim.task.data.model.ToDoEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface LocalDataSource {
    fun insertToDo(todo: ToDoEntity): Completable
    fun deleteToDo(todo: ToDoEntity): Completable
    fun updateToDo(todo: ToDoEntity): Completable
    fun getToDos(): Flowable<List<ToDoEntity>>
}