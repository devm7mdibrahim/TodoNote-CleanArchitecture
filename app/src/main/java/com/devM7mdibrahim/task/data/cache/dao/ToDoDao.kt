package com.devM7mdibrahim.task.data.cache.dao

import androidx.room.*
import com.devM7mdibrahim.task.data.cache.model.CachedToDo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(cachedToDo: CachedToDo): Completable

    @Query("SELECT * FROM TODO ORDER BY id ASC")
    fun getAllToDOs(): Flowable<List<CachedToDo>>

    @Delete
    fun delete(cachedToDo: CachedToDo): Completable

    @Update
    fun updateToDO(cachedToDo: CachedToDo): Completable
}