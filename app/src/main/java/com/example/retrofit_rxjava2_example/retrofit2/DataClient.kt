package com.example.retrofit_rxjava2_example.retrofit2

import com.example.retrofit_rxjava2_example.model.Android
import io.reactivex.Observable
import retrofit2.http.GET

interface DataClient {
    @GET("android/jsonarray/")
    abstract fun register(): Observable<List<Android>>
}