package com.example.cashappstocks

import com.example.cashappstocks.network.model.Stock
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface StockRepository {

    fun getStocks(endpoint:String) : Flow<UIState<List<Stock>>>
}