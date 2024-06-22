package com.dgu.smartcareapp.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.dgu.smartcareapp.domain.usecase.TodoListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 알람 관련 브로드 캐스트 리시버
 */
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var todoListUseCase: TodoListUseCase

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "com.dgu.smartcareapp.ALARM_ACTION" -> {
                val toDoTitle = intent.getStringExtra(TODO_TITLE)
                val toDoId = intent.getIntExtra(TODO_ID, -1)

                Log.d("dana", toDoId.toString())
                AlarmManager.emitAlarm(toDoTitle, toDoId)
            }

            Intent.ACTION_BOOT_COMPLETED -> {
                val alarmUtils = context?.let { AlarmUtils(it) }

                CoroutineScope(Dispatchers.Default).launch {
                    todoListUseCase.getTodoList().collect {
                        it.forEach {
                            alarmUtils?.setAlarm(
                                hour = it.todoHour,
                                minute = it.todoMinute,
                                toDoTitle = it.todoTitle,
                                requestCode = it.requestCode,
                                toDoId = it.id
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val TODO_TITLE = "todoTitle"
        const val TODO_ID = "todoId"
    }
}