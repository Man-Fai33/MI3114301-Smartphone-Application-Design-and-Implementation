package com.example.finaltest


import com.example.apptest.RestaurantService
import com.example.apptest.WeatherService
import com.example.apptest.weather.Record
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DataRepository {

    val retrofit = Retrofit.Builder() .baseUrl("https://opendata.cwb.gov.tw/").addConverterFactory(  MoshiConverterFactory.create( Moshi.Builder().add( KotlinJsonAdapterFactory() ).build())) .build()
    val retrofitr = Retrofit.Builder() .baseUrl("https://media.taiwan.net.tw/").addConverterFactory(  MoshiConverterFactory.create( Moshi.Builder().add( KotlinJsonAdapterFactory() ).build())) .build()

    val weatherService = retrofit.create(WeatherService::class.java)
    val restaurantService = retrofitr.create(RestaurantService::class.java)



//    fun getTemperatureForCity(city: String): List<String?> {
//        var weather: String? = null
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = weatherService.getWeather()
//            weather = response.records.location[0].weatherElement[0].time[0].parameter.parameterValue
//            val loginViewModel: LoginViewModel = LoginViewModel()
//            loginViewModel.setWeather(weather)
//            // 更新 UI 或進行其他操作
//            println("hi !! $weather")
//        }
//        return listOf(weather)
//    }
//    fun getRestaurantDataForCity(city: String) : List<com.example.apptest.model.Info> {
//        var restaurants: List<com.example.apptest.model.Info> = listOf()
//        CoroutineScope(Dispatchers.IO).launch {
//            val restaurant = restaurantService.getRestaurant()
//            restaurants = restaurant.head.infos.info
//            val loginViewModel:LoginViewModel=LoginViewModel()
//            loginViewModel.setRestaurant(restaurants)
//            // 更新 UI 或进行其他操作
//            println("hi !! $restaurants")
//        }
//        return restaurants
//    }

}
