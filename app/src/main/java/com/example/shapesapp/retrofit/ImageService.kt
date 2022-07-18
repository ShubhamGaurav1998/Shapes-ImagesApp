package com.example.shapesapp.retrofit

import com.example.shapesapp.models.ImageApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ImageService {

    @GET("{path}")
    fun getResults(@Path("path") path : String,
                           @Query("page") page: Int,
                           @Query("per_page") perPage: Int,
                           @Query("client_id") clientId: String) : Call<ImageApiResponse>
}
