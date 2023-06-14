package com.example.nyt.feature.mostPopular.data.repository.mapper

import com.example.nyt.feature.mostPopular.data.domain.data.MostPopular
import com.example.nyt.feature.mostPopular.data.remote.data.response.MostPopularResponse

fun MostPopularResponse.toDomain() =
    results.orEmpty().filterNotNull().map { MostPopular(it.title.orEmpty()) }