package com.omran.coroutineandroidkotlin.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {


    companion object {
        private val client: OkHttpClient = OkHttpClient.Builder().build()
        private const val BaseURL: String = "https://api.themoviedb.org/3/"

        fun getRetroBuilder(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }


}