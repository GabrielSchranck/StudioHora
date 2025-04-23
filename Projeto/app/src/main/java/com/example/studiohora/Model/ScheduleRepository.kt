package com.example.studiohora.Model

import AppDatabase
import androidx.room.Room
import android.content.Context
import java.util.Date

class ScheduleRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "app-database"
    ).build()

    private val scheduleDao = db.scheduleDao()

    suspend fun fetchScheduleData(): Schedule {
        return Schedule(
            cliente = "Thais",
            data = Date(),
            hora = "16:09",
            valor = 50.0
        )
    }

    suspend fun create(schedule: Schedule) {
        scheduleDao.insert(schedule)
    }

    suspend fun getAll(): List<Schedule> {
        return scheduleDao.getAllSchedules()
    }

    suspend fun update(schedule: Schedule) {
        scheduleDao.update(schedule)
    }

    suspend fun delete(schedule: Schedule) {
        scheduleDao.delete(schedule)
    }
}
