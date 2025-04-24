package com.example.schedule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedule.model.entities.Schedule

class ScheduleViewModel : ViewModel() {

    private val _scheduleList = MutableLiveData<MutableList<Schedule>>()
    val scheduleList: LiveData<MutableList<Schedule>> get() = _scheduleList

    init {
        _scheduleList.value = mutableListOf()
    }


    fun addSchedule(schedule: Schedule) {
        val currentList = _scheduleList.value ?: mutableListOf()
        currentList.add(schedule)
        _scheduleList.value = currentList
    }


}