package com.example.wiprocodingassessment.network

import com.example.wiprocodingassessment.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object BaseRetrofit {

    private var retrofit: Retrofit? = null
    private const val NETWORK_TIME = 60L


    init {
        createRetrofit()
    }

    private fun getMyRetrofit(): Retrofit {
        if (retrofit == null) {
            createRetrofit()
        }
        return retrofit!!
    }

    fun getApi(): API {
        return getMyRetrofit().create(API::class.java)
    }

    /*
        Create a Retrofit object
     */
    private fun createRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(provideOKHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides OkHttpClient object
     */
    private fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIME, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIME, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIME, TimeUnit.SECONDS)
            .build()
    }


}