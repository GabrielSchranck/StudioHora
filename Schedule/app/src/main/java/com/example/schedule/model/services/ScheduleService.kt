package com.example.schedule.repository

import com.example.schedule.model.entities.Schedule

class ScheduleRepository {

    private val schedules: MutableList<Schedule> = mutableListOf()

    fun add(schedule: Schedule): Boolean {
        return schedules.add(schedule)
    }

    fun getAll(): List<Schedule> {
        return schedules
    }

    fun getByCliente(cliente: String): List<Schedule> {
        return schedules.filter { it.Cliente == cliente }
    }

    fun update(schedule: Schedule): Boolean {
        val index = schedules.indexOfFirst { it.Cliente == schedule.Cliente && it.DataMarcada == schedule.DataMarcada }
        if (index != -1) {
            schedules[index] = schedule
            return true
        }
        return false
    }

    fun delete(schedule: Schedule): Boolean {
        return schedules.remove(schedule)
    }
}
