package com.dgu.smartcareapp.domain.usecase

import com.dgu.smartcareapp.data.model.roomdb.SafeWordData
import com.dgu.smartcareapp.domain.repository.SafeWordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SafeWordUseCase @Inject constructor(
    private val safeWordRepository: SafeWordRepository
) {
    suspend fun getAllSafeWords(): Flow<List<String>> {
        return safeWordRepository.getAllSafeWords()
    }

    suspend fun insertSafeWord(safeWord: String) {
        val safeWordData = SafeWordData(word = safeWord)
        safeWordRepository.insertSafeWord(safeWordData)
    }
}