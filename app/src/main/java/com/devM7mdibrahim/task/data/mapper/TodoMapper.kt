package com.devM7mdibrahim.task.data.mapper

import com.devM7mdibrahim.task.data.model.TodoEntity
import com.devM7mdibrahim.task.domain.model.TodoModel
import javax.inject.Inject

class TodoMapper @Inject constructor() : DataMapper<TodoModel, TodoEntity> {
    override fun mapFromDomain(domainEntity: TodoModel): TodoEntity {
        return TodoEntity(
            id = domainEntity.id,
            title = domainEntity.title,
            body = domainEntity.body
        )
    }

    override fun mapToDomain(dataEntity: TodoEntity): TodoModel {
        return TodoModel(
            id = dataEntity.id,
            title = dataEntity.title,
            body = dataEntity.body
        )
    }
}