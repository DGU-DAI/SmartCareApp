package com.dgu.smartcareapp.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dgu.smartcareapp.data.model.roomdb.DummyData
import com.dgu.smartcareapp.data.model.roomdb.SafeWordData

@Database(entities = [DummyData::class, SafeWordData::class], version = 2)
abstract class DummyDatabase : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
    abstract fun safeWordDao(): SafeWordDao
}
