package com.example.memescrawler


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://meme-api.herokuapp.com/" //gimme
class MainActivity : AppCompatActivity() {

    //private var iv_meme: ImageView? = null
    //private var title: TextView? = null
    private var recyclerview: RecyclerView? =null
    private var tv_error: TextView? = null
    private var btn_retry: Button? = null
    private var errorlayout: LinearLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_error = findViewById(R.id.tv_error)
        errorlayout = findViewById(R.id.errorlayout)
        btn_retry = findViewById(R.id.btn_error)

        btn_retry?.setOnClickListener{
            getMyData()
        }

        recyclerview= findViewById(R.id.recyclerview)
        recyclerview?.layoutManager =LinearLayoutManager(this)

        getMyData()
    }


    private fun getMyData() {

        errorlayout?.visibility = View.GONE
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Apiinterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<MyDataItem?> {

            override fun onResponse(call: Call<MyDataItem?>, response: Response<MyDataItem?>) {

                val responseBody = response.body()!!
                recyclerview?.adapter = MyAdapter(responseBody)
            }

            override fun onFailure(call: Call<MyDataItem?>, t: Throwable) {

                Log.d("MainActivity", "Failure: " + t.message)
                errorlayout?.visibility = View.VISIBLE

            }
        })
    }
}