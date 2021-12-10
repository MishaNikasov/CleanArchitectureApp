package com.nikasov.cleanarchitectureapp.data.remote

import com.nikasov.cleanarchitectureapp.common.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val urlWithApiKey = originalRequest
            .url
            .newBuilder()
            .addQueryParameter("key", Constants.API_KEY)
            .build()

        val request = originalRequest
            .newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(request = request)

    }
}