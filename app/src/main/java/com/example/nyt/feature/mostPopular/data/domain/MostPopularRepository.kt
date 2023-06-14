package com.example.nyt.feature.mostPopular.data.domain

import com.example.nyt.feature.mostPopular.data.domain.data.MostPopular
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse
import kotlinx.coroutines.flow.Flow

interface MostPopularRepository {
    fun get(type: Int): Flow<Result<List<MostPopular>>>
}