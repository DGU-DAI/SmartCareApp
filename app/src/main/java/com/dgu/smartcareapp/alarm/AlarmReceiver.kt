package com.dgu.smartcareapp.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 알람 관련 브로드 캐스트 리시버
 */
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val toDoTitle = intent?.getStringExtra(TODO_TITLE)

        Log.d("dana", toDoTitle.toString())
        AlarmManager.emitAlarm(toDoTitle)
    }

    companion object {
        const val TODO_TITLE = "todoTitle"
    }
}