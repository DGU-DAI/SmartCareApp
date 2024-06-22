package com.dgu.smartcareapp.domain.entity

import kotlinx.coroutines.flow.Flow

interface SmartCareStorage {
    var phoneNumber: String
    var isChecked: Boolean
    val isCheckedFlow: Flow<Boolean>
}
