package com.dgu.smartcareapp.data.model.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "safe_words")
data class SafeWordData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String
)
