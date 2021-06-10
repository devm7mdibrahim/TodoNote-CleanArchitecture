package com.devM7mdibrahim.task.data.cache.mapper

import com.devM7mdibrahim.task.data.cache.model.CachedToDo
import com.devM7mdibrahim.task.data.model.ToDoEntity
import javax.inject.Inject

class CachedToDoMapper @Inject constructor(): CacheMapper<CachedToDo, ToDoEntity> {
    override fun mapFromCached(cached: CachedToDo): ToDoEntity {
        return ToDoEntity(
            id = cached.id,
            title = cached.title,
            body = cached.body
        )
    }

    override fun mapToCached(entity: ToDoEntity): CachedToDo {
        return CachedToDo(
            title = entity.title,
            body = entity.body
        )
    }
}