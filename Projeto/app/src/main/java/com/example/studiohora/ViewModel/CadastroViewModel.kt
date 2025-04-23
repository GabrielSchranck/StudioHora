package com.example.studiohora.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.studiohora.Model.Schedule
import com.example.studiohora.Model.ScheduleService
import kotlinx.coroutines.launch

class CadastroViewModel(application: Application) : AndroidViewModel(application) {
    private val scheduleService: ScheduleService = ScheduleService(application)

    fun createSchedule(schedule: Schedule) {
        viewModelScope.launch {
            scheduleService.createSchedule(schedule)
        }
    }
}