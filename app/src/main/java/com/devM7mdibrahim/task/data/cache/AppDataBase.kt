package com.devM7mdibrahim.task.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devM7mdibrahim.task.data.cache.dao.TodoDao
import com.devM7mdibrahim.task.data.cache.model.CachedTodo

@Database(entities = [CachedTodo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): TodoDao
}