package com.devM7mdibrahim.task.domain.interactors

import com.devM7mdibrahim.task.domain.model.TodoModel
import com.devM7mdibrahim.task.domain.repository.TodoRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    operator fun invoke(todo: TodoModel): Completable {
        return repository.insertTodo(todo = todo)
    }
}