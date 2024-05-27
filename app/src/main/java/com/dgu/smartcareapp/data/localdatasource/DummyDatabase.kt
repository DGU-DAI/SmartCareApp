package com.dgu.smartcareapp.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dgu.smartcareapp.data.model.roomdb.DummyData

@Database(entities = [DummyData::class], version = 1)
abstract class DummyDatabase: RoomDatabase() {

    abstract fun dummyDao(): DummyDao
}