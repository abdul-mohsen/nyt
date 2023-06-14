package com.example.nyt.core.di

import com.example.nyt.feature.mostPopular.data.domain.MostPopularRepository
import com.example.nyt.feature.mostPopular.data.remote.RemoteMostPopular
import com.example.nyt.feature.mostPopular.data.remote.RemoteMostPopularImpl
import com.example.nyt.feature.mostPopular.data.repository.MostPopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMostPopularRepository(repositoryImpl: MostPopularRepositoryImpl): MostPopularRepository

}
