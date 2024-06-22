package com.dgu.smartcareapp.domain.entity

data class TodoList (
    val id: Int = 0,
    val todoTitle: String,
    val todoHour: Int,
    val todoMinute: Int,
    val todoFinish: Boolean,
    val requestCode: Int
)