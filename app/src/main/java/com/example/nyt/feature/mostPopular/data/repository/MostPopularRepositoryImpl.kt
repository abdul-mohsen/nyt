package com.example.nyt.feature.mostPopular.data.repository

import com.example.nyt.core.util.CoroutineDispatchersProvider
import com.example.nyt.core.util.DispatchersProvider
import com.example.nyt.feature.mostPopular.data.domain.MostPopularRepository
import com.example.nyt.feature.mostPopular.data.domain.data.MostPopular
import com.example.nyt.feature.mostPopular.data.remote.RemoteMostPopular
import com.example.nyt.feature.mostPopular.data.repository.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MostPopularRepositoryImpl @Inject constructor(
    private val remote: RemoteMostPopular,
    private val dispatcher: DispatchersProvider
) :
    MostPopularRepository {
    override fun get(type: Int): Flow<Result<List<MostPopular>>> = flow {
        val response = remote.get(type)
        val domain = response.map { it.toDomain() }
        emit(domain)
    }.flowOn(dispatcher.io())
}
