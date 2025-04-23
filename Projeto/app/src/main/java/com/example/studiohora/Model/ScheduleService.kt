package com.example.studiohora.Model

import android.content.Context

class ScheduleService(context: Context) {

    private val repository = ScheduleRepository(context)

    suspend fun createSchedule(schedule: Schedule) {
        repository.create(schedule)
    }

    suspend fun getAll(): List<Schedule> {
        return repository.getAll()
    }

    suspend fun update(schedule: Schedule) {
        repository.update(schedule)
    }

    suspend fun delete(schedule: Schedule) {
        repository.delete(schedule)
    }

    suspend fun concluir(schedule: Schedule) {
        // Lógica específica de concluir
    }
}
