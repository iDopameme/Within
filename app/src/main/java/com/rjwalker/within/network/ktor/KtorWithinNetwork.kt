package com.rjwalker.within.network.ktor

import com.rjwalker.within.network.model.NetworkQuote
import com.rjwalker.within.network.WithinNetworkDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.kotlinx.protobuf.protobuf
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class KtorWithinNetwork @Inject constructor(
    ktorHttpClient: HttpClient
): WithinNetworkDataSource {

    private val networkApi = ktorHttpClient

    override suspend fun getRandomQuote(): List<NetworkQuote> {
        val response = networkApi.request("https://zenquotes.io/api/today") {
            method = HttpMethod.Get
        }
        return response.body()
    }
}