package com.example.hackernetwork

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Add JSON type identifier to all requests.
 */
class JsonContentInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().url(chain.request().url.toString().plus(other = ".json")).build())
    }

}