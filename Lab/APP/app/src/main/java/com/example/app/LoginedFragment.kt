package com.example.app

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.Internal
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import android.view.View
import android.widget.*
 import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finaltest.LocationAdapter
import com.example.finaltest.LoginViewModel


class LoginedFragment:AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var viewModel: LoginViewModel
    private lateinit var locationRecyclerView: RecyclerView
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var  cposition:TextView
    //    private val dataRepository = DataRepository()
//    private lateinit var viewModel: LoginViewModel
//    private lateinit var weatherList: Record
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)


        //id
        spinner = findViewById(R.id.spinner)

        val WeatherName: TextView = findViewById(R.id.twt)
        val WeatherTemp: TextView = findViewById(R.id.twtw)
        cposition=findViewById(R.id.currentl)
        val cities = arrayOf(
            "花蓮",
            "澎湖",
            "臺東",
            "南投",
            "苗栗",
            "彰化",
            "嘉義",
            "屏東",
            "基隆",
            "臺南",
            "雲林",
            "宜蘭",
            "金門",
            "連江",
            "新竹"
        )
        try {
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
            spinner.adapter = spinnerAdapter
        } catch (e: Exception) {
            println("1" + e)
        }

        viewModel = LoginViewModel()
        viewModel.fetchWeatherAndRestaurantDataForCity("")
        locationRecyclerView = findViewById(R.id.locationRecyclerView)
        locationAdapter = LocationAdapter(emptyList())
        locationRecyclerView.adapter = locationAdapter
        locationRecyclerView.layoutManager = LinearLayoutManager(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCity = cities[position]
                // 從 restaurantDataLiveData 中獲取餐廳資料
                val restaurantData = viewModel.restaurantData.value

                // 找到地址開頭為選擇的城市的餐廳
                val selectedRestaurants = restaurantData?.filter { restaurant ->
                    restaurant.Add?.startsWith(selectedCity) == true
                }
                // 列印相對應的餐廳
                selectedRestaurants?.forEach { restaurant ->
                    println("餐廳名稱：${restaurant.Name}")
                    println("簡介：${restaurant.Description}")
                    println("地址：${restaurant.Add}")
                    println("時間：${restaurant.Opentime}")
                    println("--------------")
                }
                // 更新适配器的数据
                selectedRestaurants?.let {
                    locationAdapter.updateData(it)
                }
                fetchWeatherAndRestaurantDataForCity(selectedCity)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.weatherData.observe(this) { weather ->
            WeatherTemp.text =
                "天氣：" + weather.location[0].weatherElement[0].time[0].parameter.parameterValue
            WeatherName.text = "台灣天氣：" + weather.location[0].locationName
        }





        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !==
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            }
        }
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // 在這裡處理定位更新
                getCurrentLocation()
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                // 在這裡處理定位提供者狀態變化
//                getCurrentLocation()
            }

            override fun onProviderEnabled(provider: String) {
                getCurrentLocation()
            }

            override fun onProviderDisabled(provider: String) {
                // 在這裡處理定位提供者禁用
            }
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener
        )


    }


    private fun fetchWeatherAndRestaurantDataForCity(city: String) {

        viewModel.fetchWeatherAndRestaurantDataForCity(city)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        // 这里可以执行获取定位的逻辑
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCurrentLocation() {
        var curlocation: String = ""
        // 檢查是否有定位服務
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) {

            // 檢查定位權限
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // 定位成功
                        if (location != null) {
                            val latitude = location.latitude
                            val longitude = location.longitude

                            // 使用獲得的經緯度進行相應的處理
                            val geocoder = Geocoder(this, Locale.getDefault())
                            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                            if (addresses != null) {
                                if (addresses.isNotEmpty()) {
                                    cposition.text = "定位："+addresses[0].locality

                                }
                            }

                        }
                    }.addOnFailureListener { exception: Exception ->
                        // 定位失敗，處理錯誤
                    }
            }
        } else {
            // 定位服務未啟用，可以根據需要進行錯誤處理
        }


    }

}