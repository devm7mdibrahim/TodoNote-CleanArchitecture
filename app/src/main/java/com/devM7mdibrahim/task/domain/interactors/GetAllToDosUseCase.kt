package com.devM7mdibrahim.task.domain.interactors

import com.devM7mdibrahim.task.domain.model.ToDo
import com.devM7mdibrahim.task.domain.repository.ToDoRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetAllToDosUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    operator fun invoke(): Flowable<List<ToDo>> {
        return repository.getToDos()
    }
}