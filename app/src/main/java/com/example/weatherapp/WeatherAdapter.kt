package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(val context: Context, val articles: List<Article>): RecyclerView.Adapter<WeatherAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ArticleViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.weather.text = article.main
        holder.weatherDescription.text = article.description
        holder.itemView.setOnClickListener{
            Toast.makeText(context, article.main, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount() = articles.size

    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var weather: TextView = itemView.findViewById<TextView>(R.id.Weather)
        var weatherDescription: TextView = itemView.findViewById<TextView>(R.id.WeatherDescription)
    }

}