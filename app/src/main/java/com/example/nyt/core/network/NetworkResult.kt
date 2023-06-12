package com.example.nyt.core.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.RuntimeException
import java.lang.reflect.Type

class NetworkResult<T>(private val delegate: Call<T>) : Call<Result<T>> {
    override fun clone(): Call<Result<T>> = NetworkResult(delegate.clone())


    override fun execute(): Response<Result<T>> {
        throw UnsupportedOperationException()
    }

    override fun isExecuted() = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled() = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = handleApi(response)
                callback.onResponse(this@NetworkResult, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@NetworkResult, Response.success(Result.failure<T>(t)))
            }

        })
    }
}

fun <T> handleApi(response: Response<T>): Result<T> {
    val body = response.body()
    return if (body != null && response.isSuccessful) Result.success(body)
    else Result.failure(ApiErrorException(response.message(), response.code()))
}

class ApiErrorException(
    val msg: String,
    val code: Int
) : RuntimeException()

private inline fun <reified T> getNetworkResultAdapter() = object :CallAdapter<T, Call<Result<T>>> {
    override fun responseType(): Type = T::class.java
    override fun adapt(call: Call<T>): Call<Result<T>> = NetworkResult(call)
}


// @ssda TODO need enhancement for it to work
val networkFactory = object : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val type = getRawType(returnType)
        return getNetworkResultAdapter<Any>()
    }

}