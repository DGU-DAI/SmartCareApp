package com.dgu.smartcareapp.data.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dgu.smartcareapp.data.model.roomdb.SafeWordData
import kotlinx.coroutines.flow.Flow

@Dao
interface SafeWordDao {
    @Query("SELECT * FROM safe_words")
    fun getAllSafeWords(): Flow<List<SafeWordData>>

    @Insert
    suspend fun insertSafeWord(safeWord: SafeWordData)
}
