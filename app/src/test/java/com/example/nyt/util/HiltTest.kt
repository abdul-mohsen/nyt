package com.example.nyt.util

import com.example.nyt.core.di.CoroutineDispatcherModule
import com.example.nyt.core.util.CoroutineDispatchersProvider
import com.example.nyt.core.util.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineDispatcherModule::class]
)
abstract class FakeAnalyticsModule {

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: TestCoroutineDispatchersProvider):
            DispatchersProvider
}

class TestCoroutineDispatchersProvider @Inject constructor() : DispatchersProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.Main
    override fun unconfined(): CoroutineDispatcher = Dispatchers.Main
}