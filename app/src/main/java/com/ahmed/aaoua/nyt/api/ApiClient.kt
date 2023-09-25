package com.ahmed.aaoua.nyt.api

import android.util.Log
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType


object ApiClient {
    private const val BASE_URL = "https://api.nytimes.com"

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("ktor-log", message)
                }
            }
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            socketTimeoutMillis = 15_000
            requestTimeoutMillis = 15_000
            connectTimeoutMillis = 15_000
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    suspend fun getNews(): NYTApiResponse{
        return httpClient.get<NYTApiResponse>("$BASE_URL/svc/mostpopular/v2/viewed/1.json?api-key=Vy0evG7gNkBZnimFP6yqstJj4e6kfEHT")
    }
}