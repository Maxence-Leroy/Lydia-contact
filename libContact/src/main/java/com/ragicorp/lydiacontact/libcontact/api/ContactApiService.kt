package com.ragicorp.lydiacontact.libcontact.api

import retrofit2.http.GET
import retrofit2.http.Query


interface ContactApiService {
    @GET("api/1.3/?seed=lydia")
    suspend fun fetchContacts(
        @Query("page") page: Int,
        @Query("results") results: Int,
    ): ContactApiResponse
}