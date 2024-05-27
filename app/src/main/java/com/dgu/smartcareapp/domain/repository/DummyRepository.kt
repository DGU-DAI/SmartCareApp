package com.dgu.smartcareapp.domain.repository

import com.dgu.smartcareapp.data.model.roomdb.DummyData

interface DummyRepository {

    suspend fun insertDummy(dummy: DummyData)
}