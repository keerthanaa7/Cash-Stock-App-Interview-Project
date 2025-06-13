package com.example.cashappstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.cashappstocks.network.model.Stock

class MainActivity : ComponentActivity() {

    private val stockViewModel:StockViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = StockRepositoryImpl(RetrofitInstance.stockService)
        val factory = StockViewModelFactory(repository)
        val viewModel:StockViewModel = ViewModelProvider(this, factory)[StockViewModel::class.java]
        setContent {

            StockAppUI(stockViewModel) }
    }

    @Composable
    fun StockAppUI(stockViewModel: StockViewModel) {
        val correctendpoint =
            "https://storage.googleapis.com/cash-homework/cash-stocks-api/portfolio.json"


        val malformedendpoint = "https://storage.googleapis.com/cash-homework/cash-stocks- api/portfolio_ malformed.json"

        val emptyendpoint = "https://storage.googleapis.com/cash-homework/cash-stocks- api/portfolio_empty.json"


        LaunchedEffect(Unit) { stockViewModel.getStockList(correctendpoint) }
        val state by stockViewModel.stock.collectAsState()

        when (state){
            is UIState.Loading -> {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
            is UIState.Success -> {
                val stocks = (state as UIState.Success<List<Stock>>).data
                if(stocks.isEmpty()){
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()) {
                       Text("no stock available")
                    }
                }else {
                    LazyColumn {
                        items(stocks.size) { index ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stocks[index].name,
                                    fontSize = 24.sp,
                                    fontStyle = FontStyle.Normal,
                                    modifier = Modifier.weight(0.7f)
                                )
                                Text(
                                    text = stocks[index].current_price_cents.toString(),
                                    fontSize = 24.sp,
                                    fontStyle = FontStyle.Normal,
                                    modifier = Modifier.weight(0.3f)
                                )
                            }
                            HorizontalDivider(thickness = 2.dp)
                        }
                    }
                }
            }
            is UIState.Error -> {
                val error = (state as UIState.Error).message
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()) {
                    Text("Error ${error}", color = Color.Red)
                }
            }
        }

    }
}