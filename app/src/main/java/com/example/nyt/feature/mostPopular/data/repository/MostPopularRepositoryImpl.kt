package com.example.nyt.feature.mostPopular.data.repository

import com.example.nyt.feature.mostPopular.data.domain.MostPopularRepository
import com.example.nyt.feature.mostPopular.data.remote.RemoteMostPopular
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MostPopularRepositoryImpl @Inject constructor(private val remote: RemoteMostPopular) :
    MostPopularRepository {
    override fun get(type: Int): Flow<Result<List<MostPopularResponse.Result>>> = flow {
        emit(remote.get(type).map { it.results?.filterNotNull().orEmpty() })
    }.flowOn(Dispatchers.IO)
}