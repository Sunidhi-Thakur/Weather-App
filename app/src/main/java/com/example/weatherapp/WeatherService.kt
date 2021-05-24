package com.example.weatherapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.openweathermap.org/"
const val API_KEY = "f8ee8dc74cdbadaaf861f69cb144b9c0"

interface WeatherInterface {

    @GET("data/2.5/weather?id=524901&appid=$API_KEY")
    fun getCity(@Query("q=") city: String): Call<Weather>

}

object WeatherService{
    val weatherInstance: WeatherInterface
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherInstance = retrofit.create(WeatherInterface::class.java)
    }
}