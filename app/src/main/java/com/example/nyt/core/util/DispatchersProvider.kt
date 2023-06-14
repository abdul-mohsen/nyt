package com.example.nyt.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersProvider {
    fun io(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}

class CoroutineDispatchersProvider @Inject constructor() :DispatchersProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}