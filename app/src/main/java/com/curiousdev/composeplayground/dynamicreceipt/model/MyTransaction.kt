package com.curiousdev.composeplayground.dynamicreceipt.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class MyTransaction(
    @SerializedName("name")
    @Expose
     val name: String?,

    @SerializedName("service")
    @Expose
     val service: String?,

    @SerializedName("original_price")
    @Expose
     val originalPrice: Double?,

    @SerializedName("discount")
    @Expose
     val discount: Double?,

    @SerializedName("charges")
    @Expose
     val charges: Double?,

    @SerializedName("quantity")
    @Expose
     val quantity: Int?,

    @SerializedName("subtotal")
    @Expose
     val subtotal: Double?,

    @SerializedName("worker")
    @Expose
     val worker: String?,
)
