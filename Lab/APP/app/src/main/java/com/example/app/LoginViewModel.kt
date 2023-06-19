package com.example.finaltest

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.RestaurantService
import com.example.apptest.WeatherService
import com.example.apptest.model.Info
import com.example.apptest.weather.Record
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LoginViewModel : ViewModel() {

    private val dataRepository = DataRepository()

    private val weatherDataLiveData: MutableLiveData<Record> = MutableLiveData()
//    private var weatherDataLiveData: MutableLiveData<String?> = MutableLiveData()
    private var restaurantDataLiveData: MutableLiveData<List<Info>> = MutableLiveData()
    private val _weatherData =  MutableLiveData<Record>()
//    val weatherData: LiveData<String?> get() = weatherDataLiveData
    val weatherData: LiveData<Record> get() = weatherDataLiveData
    private val _restaurantData = MutableLiveData<List<Info>>()
    val restaurantData: LiveData<List<Info>> get() = restaurantDataLiveData

    // get api
    val retrofitr = Retrofit.Builder() .baseUrl("https://media.taiwan.net.tw/").addConverterFactory(
        MoshiConverterFactory.create( Moshi.Builder().add(
            KotlinJsonAdapterFactory() ).build()))
        .build()
    val restaurantService = retrofitr.create(RestaurantService::class.java)

    val retrofit = Retrofit.Builder() .baseUrl("https://opendata.cwb.gov.tw/").addConverterFactory(
        MoshiConverterFactory.create( Moshi.Builder().add(
            KotlinJsonAdapterFactory() ).build()))
        .build()
    val weatherService = retrofit.create(WeatherService::class.java)




    fun fetchWeatherAndRestaurantDataForCity(city: String) {
//        val temperature = dataRepository.getTemperatureForCity(city)
//        val state = dataRepository.getStateForCity(city)
//        val weatherData = WeatherData(city, temperature, state)
        var currcity =""
    if(city==""){
        currcity="台北"
    }else{
        currcity=city
    }
        viewModelScope.launch {
            try {
                val restaurant = restaurantService.getRestaurant()
                val weather = weatherService.getWeather()
            println(restaurant)
                restaurantDataLiveData.value =restaurant.head.infos.info
                weatherDataLiveData.value =weather.records


            }catch (e:Exception){
                println(e)
            }
        }
        println(   weatherDataLiveData.value)
    }

}

