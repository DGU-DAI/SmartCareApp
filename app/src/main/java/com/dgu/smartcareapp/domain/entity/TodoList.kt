package com.dgu.smartcareapp.domain.entity

data class TodoList (
    val todoTitle: String,
    val todoHour: Int,
    val todoMinute: Int,
    val todoFinish: Boolean
)