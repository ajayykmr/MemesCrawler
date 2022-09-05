package com.example.memescrawler


import retrofit2.Call
import retrofit2.http.GET

interface Apiinterface {
    @GET("gimme/100")
    fun getData(): Call<MyDataItem>
}