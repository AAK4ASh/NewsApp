package com.main.newsapplication.model.repository

import com.main.newsapplication.model.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object { //function can be called without creating an instance of a class
        private val retrofitClient by lazy {
            val loggingInterceptor=HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)//When using the BODY level, the log output will include the headers, request method,
            // request URL, request body, response code, and response body.
val client= OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        }
        val api by lazy {
            retrofitClient.create(NewsApi::class.java)}
    }

}