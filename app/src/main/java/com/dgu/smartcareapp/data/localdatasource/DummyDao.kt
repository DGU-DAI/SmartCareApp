package com.dgu.smartcareapp.data.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import com.dgu.smartcareapp.data.model.roomdb.DummyData

@Dao
interface DummyDao {

    @Insert
    suspend fun insertDummy(dummy: DummyData)
}