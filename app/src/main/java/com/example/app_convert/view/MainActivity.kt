package com.example.app_convert.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.app_convert.data.repository.CurrencyRepository
import com.example.app_convert.databinding.ActivityMainBinding
import com.example.app_convert.viewmodel.CurrencyViewModel
import com.example.app_convert.viewmodel.CurrencyViewModelFactory
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private  lateinit var viewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView((binding.root))

        val repository = CurrencyRepository()
        val factory = CurrencyViewModelFactory(repository)
        viewModel = ViewModelProvider (this, factory).get((CurrencyViewModel::class.java))

        binding.buttonConvert.setOnClickListener {
            val realValue = binding.editTextRealValue.text.toString().toDoubleOrNull()
            if(realValue != null){
                try {
                    viewModel.getCurrencies("USD-BRL,EUR-BRL") { response ->
                        response?.let {
                            try {
                                val usdRate = it.usdBrl.bid?.toDoubleOrNull() ?: 0.0
                                val eurRate = it.eurBrl.bid?.toDoubleOrNull() ?: 0.0
                                binding.textViewUsdValue.text = String.format(Locale.getDefault(), "%.2f", realValue * usdRate)
                                binding.textViewEurValue.text = String.format(Locale.getDefault(), "%.2f", realValue * eurRate)
                            } catch (e: Exception) {
                                Log.e("MainActivity", "Error on analysing answer: ${e.message}")
                                Toast.makeText(this, "Error on analysing answer", Toast.LENGTH_SHORT).show()
                            }
                        } ?: run {
                            Log.e("MainActivity", "Empty answer")
                            Toast.makeText(this, "Failed to get response from server", Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch (e: Exception) {
                    Log.e("MainActivity", "Error on searching currencies: ${e.message}")
                    Toast.makeText(this, "Error on searching currencies", Toast.LENGTH_SHORT).show()
                }
            }else{
                Log.e("MainActivity", "Input value is invalid")
                Toast.makeText(this, "Please insert a valid value", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
