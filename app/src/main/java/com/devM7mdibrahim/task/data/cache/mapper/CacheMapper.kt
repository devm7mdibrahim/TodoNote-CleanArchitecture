package com.devM7mdibrahim.task.data.cache.mapper

interface CacheMapper<CACHED, ENTITY> {
    fun mapFromCached(cached: CACHED): ENTITY
    fun mapToCached(entity: ENTITY): CACHED
}
