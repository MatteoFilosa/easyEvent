package com.example.easyEvent.network

import com.squareup.moshi.Json

data class Event(
    val id: String,
    val created_at: String,
    val title: String,
    val description: String,
    @Json(name = "image") val imageUrl: String,
    val date: String,
    val location: String,
    val location_lat: Float,
    val location_lon: Float
)
