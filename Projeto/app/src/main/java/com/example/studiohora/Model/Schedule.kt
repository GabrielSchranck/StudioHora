package com.example.studiohora.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "schedules")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var cliente: String,
    var data: Date,
    var hora: String,
    var valor: Double
)
