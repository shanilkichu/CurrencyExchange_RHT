package com.acube.sobhareader.ApiServices

import com.google.gson.JsonObject
import com.kichu.currencyexchange_rht.view.model.CurrencyData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiInterface {

     @GET("USD")
     fun getCurrencies(): Call<JsonObject>

    companion object {

        var retrofitService: ApiInterface? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): ApiInterface {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://open.er-api.com/v6/latest/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }
    }
}