package com.devM7mdibrahim.task.data.cache.mapper

import com.devM7mdibrahim.task.data.cache.model.CachedTodo
import com.devM7mdibrahim.task.data.model.TodoEntity
import javax.inject.Inject

class CachedTodoMapper @Inject constructor(): CacheMapper<CachedTodo, TodoEntity> {
    override fun mapFromCached(cached: CachedTodo): TodoEntity {
        return TodoEntity(
            id = cached.id,
            title = cached.title,
            body = cached.body
        )
    }

    override fun mapToCached(entity: TodoEntity): CachedTodo {
        return CachedTodo(
            title = entity.title,
            body = entity.body
        )
    }
}