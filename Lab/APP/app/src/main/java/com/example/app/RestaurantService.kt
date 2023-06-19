package com.example.apptest

import com.example.apptest.model.Infos
import com.example.apptest.model.Restaurant

import retrofit2.http.GET

interface RestaurantService {
    @GET("XMLReleaseALL_public/restaurant_C_f.json")
    suspend fun getRestaurant(): Restaurant
}