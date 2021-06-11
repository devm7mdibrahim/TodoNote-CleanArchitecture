package com.devM7mdibrahim.task.data.cache.dao

import androidx.room.*
import com.devM7mdibrahim.task.data.cache.model.CachedTodo
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(cachedTodo: CachedTodo): Completable

    @Query("SELECT * FROM TODO ORDER BY id ASC")
    fun getAllToDOs(): Flowable<MutableList<CachedTodo>>

    @Delete
    fun delete(cachedTodo: CachedTodo): Completable

    @Update
    fun updateToDO(cachedTodo: CachedTodo): Completable
}