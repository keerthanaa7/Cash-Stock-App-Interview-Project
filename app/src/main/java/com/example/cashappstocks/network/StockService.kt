package com.example.cashappstocks.network

import com.example.cashappstocks.network.model.Stocks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface StockService {
    @GET
    suspend fun getStocks(@Url url:String):Response<Stocks>
}