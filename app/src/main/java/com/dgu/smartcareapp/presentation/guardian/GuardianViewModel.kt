package com.dgu.smartcareapp.presentation.guardian

import androidx.lifecycle.ViewModel
import com.dgu.smartcareapp.domain.entity.SmartCareStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GuardianViewModel @Inject constructor(
    private val smartCareStorage: SmartCareStorage
) : ViewModel() {

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> get() = _phoneNumber.asStateFlow()

    init {
        getPhoneNumber()
    }

    private fun getPhoneNumber() {
        _phoneNumber.value = smartCareStorage.phoneNumber
    }

    fun setPhoneNumber(newPhoneNumber: String) {
        smartCareStorage.phoneNumber = newPhoneNumber
    }

}