package com.example.nyt.feature.mostPopular.data.remote

import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse

interface RemoteMostPopular {
    suspend fun get(type: Int): Result<MostPopularResponse>
}