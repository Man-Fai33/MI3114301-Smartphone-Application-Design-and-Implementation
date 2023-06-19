package com.example.apptest.model

import com.squareup.moshi.Json

data class Header (
    @Json(name="Infos")
    val infos: Infos
    )