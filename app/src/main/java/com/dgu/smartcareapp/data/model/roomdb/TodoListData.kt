package com.dgu.smartcareapp.data.model.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class TodoListData (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val todoTitle: String,
    val todoHour: Int,
    val todoMinute: Int,
    val todoFinish: Boolean,
    val requestCode: Int
)