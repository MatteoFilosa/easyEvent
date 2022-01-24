package com.example.easyEvent.network

import com.example.easyEvent.model.EventModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @Headers("apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlhdCI6MTY0MjA4MDk5MiwiZXhwIjoxOTU3NjU2OTkyfQ.8_5-1smAi3jcbnUJ40sVJ693PlA1kGD7EdONOvUpaBI")
    @GET("events")
    fun fetchAllEvents(): Call<List<EventModel>>
}