package com.devM7mdibrahim.task.data.mapper

interface DataMapper<DOMAIN_ENTITY, DATA_ENTITY> {
    fun mapFromDomain(domainEntity: DOMAIN_ENTITY): DATA_ENTITY
    fun mapToDomain(dataEntity: DATA_ENTITY): DOMAIN_ENTITY
}
