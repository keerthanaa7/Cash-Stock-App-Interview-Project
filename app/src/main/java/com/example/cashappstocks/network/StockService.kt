package com.example.cashappstocks.network

import com.example.cashappstocks.network.model.Stocks
import retrofit2.Response
import retrofit2.http.GET

interface StockService {
    @GET("portfolio.json")
    suspend fun getStocks():Stocks

    @GET("portfolio_malformed.json")
    suspend fun getMalformedStocks():Stocks

    @GET("portfolio_empty.json")
    suspend fun getEmptyStocks():Stocks
}