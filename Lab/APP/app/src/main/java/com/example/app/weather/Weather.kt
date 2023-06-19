package com.example.apptest.weather

import com.squareup.moshi.Json

data class Weather(
    @Json(name="records")
    val records:Record

)