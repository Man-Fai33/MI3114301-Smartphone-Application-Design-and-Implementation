package com.example.apptest.model

import com.squareup.moshi.Json

data class Infos (
    @Json(name = "Info")
    val  info: List<Info>
)