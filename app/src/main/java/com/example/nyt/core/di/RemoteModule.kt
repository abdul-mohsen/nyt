package com.example.nyt.core.di

import com.example.nyt.feature.mostPopular.data.remote.RemoteMostPopular
import com.example.nyt.feature.mostPopular.data.remote.RemoteMostPopularImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    abstract fun bindRemoteMostPopular(remoteImpl: RemoteMostPopularImpl): RemoteMostPopular
}