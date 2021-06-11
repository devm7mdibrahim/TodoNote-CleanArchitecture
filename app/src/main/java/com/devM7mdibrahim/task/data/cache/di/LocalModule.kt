package com.devM7mdibrahim.task.data.cache.di

import android.content.Context
import androidx.room.Room
import com.devM7mdibrahim.task.data.cache.AppDatabase
import com.devM7mdibrahim.task.data.cache.DATABASE_NAME
import com.devM7mdibrahim.task.data.cache.dao.TodoDao
import com.devM7mdibrahim.task.data.cache.datasource.LocalDataSourceImpl
import com.devM7mdibrahim.task.data.cache.mapper.CachedTodoMapper
import com.devM7mdibrahim.task.data.datasource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(database: AppDatabase): TodoDao = database.toDoDao()


    @Singleton
    @Provides
    fun provideLocalDataSource(dao: TodoDao, mapper: CachedTodoMapper): LocalDataSource =
        LocalDataSourceImpl(dao, mapper)

}