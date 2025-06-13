package com.example.cashappstocks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cashappstocks.network.model.Stock
import com.example.cashappstocks.network.model.Stocks

class MainActivity : ComponentActivity() {
    private lateinit var stockViewModel: StockViewModel
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = StockViewModelFactory()
        stockViewModel = ViewModelProvider(this, factory)[StockViewModel::class.java]
        stockViewModel.getStockList()
        setContent { stockAppUI() }
    }

    @Composable
    fun stockAppUI() {
            var stockResponseState = stockViewModel.getStockList().observeAsState()
            var stockList: List<Stock>? = stockResponseState.value
        LazyColumn {
            stockList?.size?.let {
                items(it) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stockList[index].name,
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Normal,
                            modifier = Modifier.weight(0.7f)
                        )
                        Text(
                            text = stockList.get(index).current_price_cents.toString(),
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
}