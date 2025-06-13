package com.example.cashappstocks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cashappstocks.network.model.Stock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {
    companion object {
        val TAG = "StockViewModel"
    }
    private val stocks = MutableStateFlow<UIState<List<Stock>>>(UIState.Loading)
    val stock:StateFlow<UIState<List<Stock>>> = stocks.asStateFlow()

    fun getStockList(endpoint: String){
        viewModelScope.launch {
            repository.getStocks(endpoint).collect{
                stocks.value = it
            }
        }
    }
}