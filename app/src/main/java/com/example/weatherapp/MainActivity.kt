package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: WeatherAdapter
    lateinit var binding: ActivityMainBinding
    private var articles = mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WeatherAdapter(this@MainActivity, articles)
        binding.WeatherList.adapter = adapter

        binding.WeatherList.layoutManager = LinearLayoutManager(this@MainActivity)
        getData()
    }

    private fun getData() {
        var city = binding.cityName.query.toString()
        binding.cityName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val news: Call<Weather> = WeatherService.weatherInstance.getCity(city)

                news.enqueue(object : Callback<Weather> {
                    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                        if (response.code() == 404)
                            Toast.makeText(this@MainActivity, "ENTER A VALID CITY", Toast.LENGTH_SHORT)
                                .show()
                        val news: Weather? = response.body()
                        if (news != null) {
                            Log.d("SUNIDHI", city)
                            articles.addAll(news.weather)
                            adapter.notifyDataSetChanged() //Re-render data
                        }

                    }

                    override fun onFailure(call: Call<Weather>, t: Throwable) {
                        Log.d("SUNIDHI", "Error")
                    }

                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val news: Call<Weather> = WeatherService.weatherInstance.getCity(city)

                news.enqueue(object : Callback<Weather> {
                    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                        if (response.code() == 404)
                            Toast.makeText(this@MainActivity, "ENTER A VALID CITY", Toast.LENGTH_SHORT)
                                .show()
                        val news: Weather? = response.body()
                        if (news != null) {
                            Log.d("SUNIDHI", city)
                            articles.addAll(news.weather)
                            adapter.notifyDataSetChanged() //Re-render data
                        }

                    }

                    override fun onFailure(call: Call<Weather>, t: Throwable) {
                        Log.d("SUNIDHI", "Error")
                    }

                })
                return false
            }
        })




//            val news: Call<Weather> = WeatherService.weatherInstance.getCity(city)
//            news.enqueue(object : Callback<Weather> {
//                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
//                    if (response.code() == 404)
//                        Toast.makeText(this@MainActivity, "ENTER A VALID CITY", Toast.LENGTH_SHORT)
//                            .show()
//                    val news: Weather? = response.body()
//                    if (news != null) {
//                        Log.d("SUNIDHI", city)
//                        articles.addAll(news.weather)
//                        adapter.notifyDataSetChanged() //Re-render data
//                    }
//
//                }
//
//                override fun onFailure(call: Call<Weather>, t: Throwable) {
//                    Log.d("SUNIDHI", "Error")
//                }
//
//            })
        }
    }