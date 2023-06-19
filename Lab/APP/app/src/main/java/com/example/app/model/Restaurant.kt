package com.example.apptest.model

import com.example.apptest.weather.Record
import com.squareup.moshi.Json

data class Restaurant (
    @Json(name="XML_Head")
    val head: Header
)