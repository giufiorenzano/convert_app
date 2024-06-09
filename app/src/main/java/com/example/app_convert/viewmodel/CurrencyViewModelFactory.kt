package com.example.app_convert.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_convert.data.repository.CurrencyRepository

class CurrencyViewModelFactory (private val repository: CurrencyRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelclass: Class<T>): T {
        if(modelclass.isAssignableFrom(CurrencyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrencyViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel not found")
    }
}