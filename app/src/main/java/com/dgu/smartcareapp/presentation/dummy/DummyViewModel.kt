package com.dgu.smartcareapp.presentation.dummy

import androidx.lifecycle.ViewModel
import com.dgu.smartcareapp.domain.usecase.DummyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
): ViewModel() {
}