package com.devM7mdibrahim.task.domain.interactors

import com.devM7mdibrahim.task.domain.model.TodoModel
import com.devM7mdibrahim.task.domain.repository.TodoRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetAllTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flowable<MutableList<TodoModel>> {
        return repository.getTodos()
    }
}