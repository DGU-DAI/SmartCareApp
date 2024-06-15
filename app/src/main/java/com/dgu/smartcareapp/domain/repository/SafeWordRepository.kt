package com.dgu.smartcareapp.domain.repository

import com.dgu.smartcareapp.data.model.roomdb.SafeWordData
import kotlinx.coroutines.flow.Flow

interface SafeWordRepository {

    suspend fun getAllSafeWords(): Flow<List<String>>

    suspend fun insertSafeWord(safeWord: SafeWordData)
}