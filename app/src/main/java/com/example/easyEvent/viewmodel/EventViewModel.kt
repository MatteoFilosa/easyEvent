package com.example.easyEvent.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.easyEvent.model.EventModel
import com.example.easyEvent.model.EventRepository

class EventViewModel(application: Application): AndroidViewModel(application) {

    private var eventRepository: EventRepository? = null
    var eventModelListLiveData: LiveData<List<EventModel>>? = null

    init {
        eventRepository = EventRepository()
        eventModelListLiveData = MutableLiveData()
    }

    fun fetchAllEvents() {
        eventModelListLiveData = eventRepository?.fetchAllEvents()
    }
}