package com.dgu.smartcareapp.data.repository

import com.dgu.smartcareapp.data.localdatasource.DummyDao
import com.dgu.smartcareapp.data.model.roomdb.DummyData
import com.dgu.smartcareapp.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyDao: DummyDao
): DummyRepository {

    override suspend fun insertDummy(dummy: DummyData) {
        dummyDao.insertDummy(dummy)
    }
}