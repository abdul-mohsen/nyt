package com.example.nyt.feature.mostPopular.data.repository

import com.example.nyt.feature.mostPopular.data.domain.MostPopularRepository
import com.example.nyt.feature.mostPopular.data.remote.RemoteMostPopular
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MostPopularRepositoryImpl constructor(private val remote: RemoteMostPopular): MostPopularRepository {
    override fun get(type: Int): Flow<Result<MostPopularResponse>> = flow {
        emit(remote.get(type))
    }
}