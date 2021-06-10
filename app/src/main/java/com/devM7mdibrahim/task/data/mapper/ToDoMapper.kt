package com.devM7mdibrahim.task.data.mapper

import com.devM7mdibrahim.task.data.model.ToDoEntity
import com.devM7mdibrahim.task.domain.model.ToDo
import javax.inject.Inject

class ToDoMapper @Inject constructor() : DataMapper<ToDo, ToDoEntity> {
    override fun mapFromDomain(domainEntity: ToDo): ToDoEntity {
        return ToDoEntity(
            id = domainEntity.id,
            title = domainEntity.title,
            body = domainEntity.body
        )
    }

    override fun mapToDomain(dataEntity: ToDoEntity): ToDo {
        return ToDo(
            id = dataEntity.id,
            title = dataEntity.title,
            body = dataEntity.body
        )
    }
}