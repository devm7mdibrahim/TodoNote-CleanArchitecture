package com.devM7mdibrahim.task.domain.repository

import com.devM7mdibrahim.task.domain.model.ToDo
import io.reactivex.Completable
import io.reactivex.Flowable


interface ToDoRepository {
    fun insertToDo(toDo: ToDo): Completable
    fun deleteToDo(toDo: ToDo): Completable
    fun updateToDo(toDo: ToDo): Completable
    fun getToDos(): Flowable<List<ToDo>>
}