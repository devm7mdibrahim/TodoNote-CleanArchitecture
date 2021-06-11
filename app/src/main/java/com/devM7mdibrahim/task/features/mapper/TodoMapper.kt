package com.devM7mdibrahim.task.features.mapper

import com.devM7mdibrahim.task.domain.model.TodoModel
import com.devM7mdibrahim.task.features.model.TodoItem
import javax.inject.Inject

class TodoMapper @Inject constructor(): Mapper<TodoItem, TodoModel> {
    override fun mapFromView(type: TodoItem): TodoModel {
        return TodoModel(
            id = type.id,
            title = type.title,
            body = type.body
        )
    }

    override fun mapToView(type: TodoModel): TodoItem {
        return TodoItem(
            id = type.id,
            title = type.title,
            body = type.body
        )
    }
}