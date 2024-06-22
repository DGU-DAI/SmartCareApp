package com.dgu.smartcareapp.di

import android.content.Context
import androidx.room.Room
import com.dgu.smartcareapp.data.localdatasource.DummyDao
import com.dgu.smartcareapp.data.localdatasource.DummyDatabase
import com.dgu.smartcareapp.data.localdatasource.SafeWordDao
import com.dgu.smartcareapp.data.localdatasource.TodoListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDummyDatabase(@ApplicationContext context: Context): DummyDatabase {
        return Room.databaseBuilder(
            context,
            DummyDatabase::class.java,
            "dummy_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDummyDao(dummyDatabase: DummyDatabase): DummyDao {
        return dummyDatabase.dummyDao()
    }

    @Provides
    @Singleton
    fun provideSafeWordDao(dummyDatabase: DummyDatabase): SafeWordDao {
        return dummyDatabase.safeWordDao()
    }

    @Provides
    @Singleton
    fun provideTodoListDao(dummyDatabase: DummyDatabase): TodoListDao {
        return dummyDatabase.todoListDao()
    }
}
