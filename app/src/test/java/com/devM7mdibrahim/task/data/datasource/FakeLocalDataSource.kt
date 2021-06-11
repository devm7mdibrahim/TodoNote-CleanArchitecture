package com.devM7mdibrahim.task.data.datasource

import com.devM7mdibrahim.task.data.model.TodoEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class FakeLocalDataSource(private var todos: MutableList<TodoEntity> = mutableListOf()) :
    LocalDataSource {

    var observable = Flowable.fromArray(todos)

    override fun insertTodo(todo: TodoEntity): Completable {
        return todos.let {
            it.add(todo)
            refreshObservable()
            Completable.complete()
        }
    }

    override fun deleteTodo(todo: TodoEntity): Completable {
        return todos.let {
            it.remove(todo)
            refreshObservable()
            Completable.complete()
        }
    }

    override fun updateTodo(todo: TodoEntity): Completable {

        val mTodo = todos.find { it.id == todo.id }

        mTodo?.let {
            it.title = todo.title
            it.body = todo.body

            refreshObservable()
            return Completable.complete()
        } ?: return Completable.error(Exception("not found"))
    }

    override fun getTodos(): Flowable<MutableList<TodoEntity>> {
        return observable
    }

    private fun refreshObservable() {
        observable = Flowable.fromArray(todos)
    }
}