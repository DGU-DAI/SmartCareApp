package com.dgu.smartcareapp.data.model.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DummyData (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val content: String
)