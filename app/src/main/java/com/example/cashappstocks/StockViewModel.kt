package com.example.cashappstocks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cashappstocks.network.StockService
import com.example.cashappstocks.network.model.Stock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StockViewModel(private val stockService: StockService) : ViewModel() {
    companion object {
        val TAG = "StockViewModel"
    }

    private var stockListLiveData: MutableLiveData<List<Stock>> = MutableLiveData()
    var stockListData: LiveData<List<Stock>> = stockListLiveData

    fun getStockList(): LiveData<List<Stock>> = liveData(Dispatchers.IO) {
        var stocksResponse: List<Stock>
        try {
            stocksResponse = stockService.getStocks().stocks
            stocksResponse.let {
                emit(stocksResponse)
            }
            withContext(Dispatchers.Main) {
                stockListLiveData.value = stocksResponse
                Log.d(TAG, "size " + stockListData.value.size)
            }

        } catch (e: Exception) {
            Log.d(TAG, "malformed response " + e)
        }
    }
}