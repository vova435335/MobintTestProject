package dev.vladimir.mobinttestproject.data

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("TOKEN", "123")
        return chain.proceed(request.build())
    }
}