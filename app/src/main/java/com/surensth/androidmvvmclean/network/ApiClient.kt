package com.surensth.androidmvvmclean.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory.create

/**
 * Created by surensth on 27 May,2018
 */
class ApiClient {

    companion object {

        private const val BASE_URL = "https://api.coinmarketcap.com/v1/"

        fun getClient(): Retrofit {
            val okHttpClient = OkHttpClient.Builder().build()
            val moshi = Moshi.Builder().build()

            return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                    .addConverterFactory(create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}