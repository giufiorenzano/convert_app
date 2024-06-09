package com.example.app_convert.data.model

import com.google.gson.annotations.SerializedName

class CurrencyResponse (
    @SerializedName("USD-BRL") val usdBRL: CurrencyInfo,
    @SerializedName("EUR-BRL") val eurBRL: CurrencyInfo
)

data class CurrencyInfo(
    val code: String,
    val codein: String,
    val name: String,
    val high: String,
    val low: String,
    val varBid: String,
    val pctChange: String,
    val bid: String,
    val ask: String,
    val timestamp: String,
    val create_date: String
)