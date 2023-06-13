package com.example.nyt.feature.mostPopular.data.remote.api

import com.example.nyt.BuildConfig
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MostPopularApi {
    @GET(BuildConfig.MostPopularApi)
    suspend fun get(@Path("type") type: Int): Response<MostPopularResponse>
}