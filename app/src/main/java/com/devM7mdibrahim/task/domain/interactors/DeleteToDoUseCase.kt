package com.devM7mdibrahim.task.domain.interactors

import com.devM7mdibrahim.task.domain.model.ToDo
import com.devM7mdibrahim.task.domain.repository.ToDoRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    operator fun invoke(toDo: ToDo): Completable{
        return repository.deleteToDo(toDo = toDo)
    }
}