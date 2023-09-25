package com.ahmed.aaoua.nyt.api

import io.ktor.client.HttpClient

class Repository(private val apiClient: ApiClient) {

    suspend fun getNews(): NYTApiResponse {
        return apiClient.getNews()
    }

}