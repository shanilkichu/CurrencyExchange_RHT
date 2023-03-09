package com.kichu.currencyexchange_rht.view.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpinnerData {
    var key: String? = null
    var usdValue: String? = null

    constructor(
        key: String?,
        usdValue: String?,
    ): super() {
        this.key = key
        this.usdValue = usdValue
    }

    override fun toString(): String {
        return key.toString()
    }

}