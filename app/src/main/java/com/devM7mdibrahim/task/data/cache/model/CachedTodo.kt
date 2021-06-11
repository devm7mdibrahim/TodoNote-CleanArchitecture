package com.devM7mdibrahim.task.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devM7mdibrahim.task.data.cache.TODO_TABLE_NAME

@Entity(tableName = TODO_TABLE_NAME)
class CachedTodo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val body: String?
)