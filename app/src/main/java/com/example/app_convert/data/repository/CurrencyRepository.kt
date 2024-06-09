package com.example.app_convert.data.repository

import com.example.app_convert.network.CurrencyApiService
import com.example.app_convert.network.RetrofitInstance

class CurrencyRepository {
    private val apiService: CurrencyApiService = RetrofitInstance.api
    suspend fun getCurrencies(currencies: String) = apiService.getCurrencies(currencies)
}