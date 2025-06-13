package com.example.cashappstocks

import com.example.cashappstocks.network.StockService
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class StockViewModelTest{
    private lateinit var stockViewModel: StockViewModel
    private lateinit var stockService: StockService

    @Before
    fun setUp() {
        stockService = Mockito.mock(StockService::class.java)
    }

    @Test
    fun getStockList(){

    }
}