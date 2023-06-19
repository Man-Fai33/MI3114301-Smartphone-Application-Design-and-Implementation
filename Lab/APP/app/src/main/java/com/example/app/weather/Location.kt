package com.example.apptest.weather

import com.squareup.moshi.Json

data class Location (
    @Json(name="locationName")
    val locationName:String,
    val weatherElement:List<WeatherElement>
        )