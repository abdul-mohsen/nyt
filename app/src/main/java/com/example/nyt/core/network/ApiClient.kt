package com.example.nyt.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit


object ApiClient {
    private const val baseUrl = "api.nytimes.com"
    private const val apikey = "XSaGklVBZVeMhL9oVk7fFVbFAYU01TeV"

    private val clientInterceptor: Interceptor = Interceptor { chain ->
        val request = chain.request().apply {
            newBuilder().url(
                url.newBuilder()
                    .addQueryParameter("api-key", apikey)
                    .build()
            )
        }
        val response = chain.proceed(request)
        when(response.code) {
            403 -> handleForbiddenResponse()
        }
        response
    }


    private val clientBuilder = OkHttpClient.Builder().addInterceptor(clientInterceptor).build()

    val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder)
//            .addConverterFactory(Json)
            .build()
    }

    private fun handleForbiddenResponse() {

    }

    inline fun <reified T> createService(): T =
        retrofit.create(T::class.java)

}