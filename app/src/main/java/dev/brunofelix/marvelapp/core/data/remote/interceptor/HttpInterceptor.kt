package dev.brunofelix.marvelapp.core.data.remote.interceptor

import dev.brunofelix.marvelapp.BuildConfig
import dev.brunofelix.marvelapp.core.extension.toMd5Hash
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentTimestamp = System.currentTimeMillis()
        val publicKey = BuildConfig.PUBLIC_KEY
        val privateKey = BuildConfig.PRIVATE_KEY
        val encrypted = currentTimestamp.toString() + privateKey + publicKey

        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("ts", currentTimestamp.toString())
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", encrypted.toMd5Hash())
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}