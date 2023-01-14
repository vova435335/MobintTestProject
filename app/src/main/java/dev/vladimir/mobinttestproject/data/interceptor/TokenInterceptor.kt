package dev.vladimir.mobinttestproject.data.interceptor

import dev.vladimir.mobinttestproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val TOKEN_HEADER_NAME = "TOKEN"

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(TOKEN_HEADER_NAME, BuildConfig.TOKEN)
        return chain.proceed(request.build())
    }
}

