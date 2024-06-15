package com.dgu.smartcareapp.di

import android.content.Context
import androidx.room.Room
import com.dgu.smartcareapp.data.localdatasource.DummyDao
import com.dgu.smartcareapp.data.localdatasource.DummyDatabase
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
        ).build()
    }

    @Provides
    fun provideDummyDao(dummyDatabase: DummyDatabase): DummyDao {
        return dummyDatabase.dummyDao()
    }
}
