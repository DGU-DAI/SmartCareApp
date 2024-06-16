package com.dgu.smartcareapp.data.repository

import com.dgu.smartcareapp.data.localdatasource.SafeWordDao
import com.dgu.smartcareapp.data.model.roomdb.SafeWordData
import com.dgu.smartcareapp.domain.repository.SafeWordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SafeWordRepositoryImpl @Inject constructor(
    private val safeWordDao: SafeWordDao
) : SafeWordRepository {
    override suspend fun getAllSafeWords(): Flow<List<String>> {
        return safeWordDao.getAllSafeWords().map { list ->
            list.map { it.word }
        }
    }

    override suspend fun insertSafeWord(safeWord: SafeWordData) {
        safeWordDao.insertSafeWord(safeWord)
    }

}