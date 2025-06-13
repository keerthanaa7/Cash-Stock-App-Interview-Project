package com.example.cashappstocks

import com.example.cashappstocks.network.StockService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        val BASE_URL = "https://storage.googleapis.com/cash-homework/cash-stocks-api/"
        val stockService:StockService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(StockService::class.java)
        }
    }
}