package com.kichu.currencyexchange_rht.view.Api

import com.acube.sobhareader.ApiServices.ApiInterface

class MainRepository constructor(private val retrofitService: ApiInterface) {

     fun getallCurrency() = retrofitService.getCurrencies()
}