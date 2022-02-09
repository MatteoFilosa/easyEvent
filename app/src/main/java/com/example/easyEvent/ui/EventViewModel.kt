package com.example.easyEvent.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyEvent.network.Api
import com.example.easyEvent.network.Event
import kotlinx.coroutines.launch
import java.util.*

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

    fun getEventList(location: String) {
        if (location.isEmpty()) {
            viewModelScope.launch {
                _status.value = ApiStatus.LOADING
                try {
                    // val listResult = Api.retrofitService.getAllEvents()
                    _events.value = Api.retrofitService.getAllEvents()
                    _status.value = ApiStatus.DONE
                } catch (e: Exception) {
                    Log.d("EventViewModel", "getEventList: Error $e")
                    _events.value = listOf()
                    _status.value = ApiStatus.ERROR
                }
            }
        } else {
            viewModelScope.launch {
                _status.value = ApiStatus.LOADING
                try {
                    val listResult = Api.retrofitService.getAllEvents()

                    val filterList = listResult.filter {
                        it.location.lowercase(Locale.ROOT).startsWith(location)
                    }
                    _events.value = filterList
                    _status.value = ApiStatus.DONE
                } catch (e: Exception) {
                    Log.d("EventViewModel", "getEventList: Error $e")
                    _events.value = listOf()
                    _status.value = ApiStatus.ERROR
                }
            }
        }
    }

    /* fun searchEventsByLocation(location: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val entireList = Api.retrofitService.getAllEvents()
                var queryList: MutableList<Event> = mutableListOf()
                entireList.forEach() { event ->
                    if (event.location.equals(location))
                        queryList.add(event)
                }

                _events.value = queryList
                _status.value = ApiStatus.DONE

            } catch (e: Exception) {
                Log.d("EventViewModel", "getEventList: Error $e")
                _events.value = listOf()
                _status.value = ApiStatus.ERROR
            }
        }
    } */

    fun onEventClicked(event: Event) {
        _event.value = event
    }
}