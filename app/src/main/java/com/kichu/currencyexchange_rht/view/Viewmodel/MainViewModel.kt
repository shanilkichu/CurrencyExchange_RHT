package com.kichu.currencyexchange_rht.view.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.google.gson.JsonObject
import com.kichu.currencyexchange_rht.view.Api.MainRepository
import com.kichu.currencyexchange_rht.view.model.CurrencyData
import com.kichu.currencyexchange_rht.view.model.Rates
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel (private val repository: MainRepository) : ViewModel() {

    val currencyList = MutableLiveData<JsonObject>()
    val errorMessage = MutableLiveData<String>()
    fun getallCurrency() {
        val response = repository.getallCurrency()
        response.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                currencyList.postValue(response.body())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }
}