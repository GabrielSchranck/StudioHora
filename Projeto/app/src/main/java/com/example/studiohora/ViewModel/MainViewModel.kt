package com.example.studiohora.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studiohora.Model.Schedule
import com.example.studiohora.Model.ScheduleService
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val scheduleService: ScheduleService = ScheduleService(application)

    private val _schedules = MutableLiveData<List<Schedule>>()
    val schedules: LiveData<List<Schedule>> = _schedules

    init {
        getAllSchedules()
    }

    fun getAllSchedules() {
        viewModelScope.launch {
            val result = scheduleService.getAll()
            _schedules.postValue(result)
        }
    }

    fun updateSchedule(schedule: Schedule) {
        viewModelScope.launch {
            scheduleService.update(schedule)
            getAllSchedules()
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.launch {
            scheduleService.delete(schedule)
            getAllSchedules()
        }
    }
}
