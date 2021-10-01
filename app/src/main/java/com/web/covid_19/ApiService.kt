package com.web.covid_19

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    val BASE_URL: String = "http://192.168.165.59/tema_crud/Api/"
    val IMAGE_URL: String = "http://192.168.165.59/tema_crud/assets/images/img/"

    fun getOkhttps(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkhttps())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getApiEndPoint(): ApiEndpoint {
        return getRetrofit().create(ApiEndpoint::class.java)

    }
}