package com.example.easyEvent.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyEvent.network.Api
import com.example.easyEvent.network.Event
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class EventViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    private val _events = MutableLiveData<List<Event>>()
    private val _event = MutableLiveData<Event>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status
    val events: LiveData<List<Event>> = _events
    val event: LiveData<Event> = _event

    init {
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val listResult = Api.retrofitService.getAllEvents()
                _events.value = Api.retrofitService.getAllEvents()
                _status.value = ApiStatus.DONE
            }
            catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _events.value = listOf()
            }
        }
    }

    fun onEventClicked(event: Event) {
        _event.value = event
    }
}