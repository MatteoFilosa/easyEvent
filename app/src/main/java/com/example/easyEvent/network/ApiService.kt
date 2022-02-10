package com.example.easyEvent.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://uzmnueecvzffxfxvofie.supabase.co/rest/v1/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @Headers("apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlhdCI6MTY0MjA4MDk5MiwiZXhwIjoxOTU3NjU2OTkyfQ.8_5-1smAi3jcbnUJ40sVJ693PlA1kGD7EdONOvUpaBI")
    @GET("events")
    suspend fun getAllEvents(): List<Event>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
