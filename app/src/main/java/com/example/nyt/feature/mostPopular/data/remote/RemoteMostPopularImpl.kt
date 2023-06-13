package com.example.nyt.feature.mostPopular.data.remote

import com.example.nyt.core.network.ApiClient
import com.example.nyt.core.network.handleApi
import com.example.nyt.feature.mostPopular.data.remote.api.MostPopularApi
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse
import javax.inject.Inject

class RemoteMostPopularImpl @Inject constructor(): RemoteMostPopular {

    private val api: MostPopularApi = ApiClient.createService()

    override suspend fun get(type: Int): Result<MostPopularResponse> =
        handleApi(api.get(type))
}

