package com.example.hackernetwork

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object providing a [Retrofit] instance
 */
object HackerNewsRetrofit {

    private const val baseUrl = "https://hacker-news.firebaseio.com/v0/"

    private val baseClient: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC} )
                .addInterceptor(JsonContentInterceptor())
                .build()
        }

    val retrofitInstance: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(baseClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}