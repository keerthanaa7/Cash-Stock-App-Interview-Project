package com.example.cashappstocks

import com.example.cashappstocks.network.StockService
import com.example.cashappstocks.network.model.Stock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class StockRepositoryImpl(private val stockService: StockService):StockRepository {
    override fun getStocks(endpoint: String): Flow<UIState<List<Stock>>>  = flow {
        emit(UIState.Loading)
        try {
            val response = stockService.getStocks(endpoint)
            if(response.isSuccessful){
                val body = response.body()
                if(body?.stocks.isNullOrEmpty()){
                    emit(UIState.Success(emptyList()))
                }else{
                    emit(UIState.Success(body!!.stocks))
                }
            } else {
                emit(UIState.Error("Stock service error :${response.code()}"))
            }
        } catch (e:Exception){
            emit(UIState.Error("Network Error: ${e.message?: "unknown error"}"))
        }
    }
}