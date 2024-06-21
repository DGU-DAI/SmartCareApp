package com.dgu.smartcareapp.alarm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * todo naming 고민
 * 알람이 울릴 때 전역적으로 방출하기 위해 사용하는 flow
 * 전역적으로 해당 flow 하나만 존재하게 하기 위해서 object로 구현
 */
object AlarmManager {
    private val _alarm = MutableSharedFlow<String?>(1)
    val alarm = _alarm.asSharedFlow()

    fun emitAlarm(toDoTitle: String?) {
        CoroutineScope(Dispatchers.Default).launch {
            _alarm.emit(toDoTitle)
        }
    }
}