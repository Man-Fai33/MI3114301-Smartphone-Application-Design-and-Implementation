package com.example.apptest

import com.example.apptest.weather.Weather
import retrofit2.http.GET
import retrofit2.http.Headers

//private const val apiKey:String='CWB-5F6FD6CB-6D23-416E-AD59-C2C953E30929'
interface WeatherService {
    @Headers("Authorization: CWB-5F6FD6CB-6D23-416E-AD59-C2C953E30929")
    @GET("api/v1/rest/datastore/F-C0032-001/")
    suspend fun getWeather(): Weather
}