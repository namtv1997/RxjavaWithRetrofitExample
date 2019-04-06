package com.example.retrofit_rxjava2_example.model

import com.google.gson.annotations.SerializedName

class Android(
    @SerializedName("ver")
    var ver:String,
    @SerializedName("name")
    var name:String,
    @SerializedName("api")
    var api:String
)