package com.dgu.smartcareapp.domain.usecase

import com.dgu.smartcareapp.data.model.roomdb.DummyData
import com.dgu.smartcareapp.domain.repository.DummyRepository
import javax.inject.Inject

class DummyUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {

    suspend operator fun invoke(dummy: DummyData) = dummyRepository.insertDummy(dummy)
}