package com.example.cashappstocks

import android.util.Log
import androidx.compose.ui.graphics.StrokeCap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cashappstocks.network.StockService
import com.example.cashappstocks.network.model.Stock
import com.example.cashappstocks.network.model.Stocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    val retService = RetrofitInstance.getRetrofitInstance().create(StockService::class.java)

    companion object {
        val TAG = "StockViewModel"
    }

    val stockList: LiveData<List<Stock>> = liveData(Dispatchers.IO) {
        val stocksResponse = retService.getStocks().stocks
        stocksResponse.let {
            Log.d(TAG, "stocks " + stocksResponse.size)
            emit(stocksResponse)
        }
    }
}