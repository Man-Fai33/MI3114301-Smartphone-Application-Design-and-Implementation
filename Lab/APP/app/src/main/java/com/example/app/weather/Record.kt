package com.example.apptest.weather

import com.squareup.moshi.Json

data class Record (
    @Json(name="location")
   val location:List<Location>)