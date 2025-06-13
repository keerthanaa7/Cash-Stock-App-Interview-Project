package com.example.cashappstocks.network.model

import com.google.gson.annotations.SerializedName

data class Stock(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("current_price_cents")
    val current_price_cents: Int,
    @SerializedName("current_price_timestamp")
    val current_price_timestamp: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("ticker")
    val ticker: String
)