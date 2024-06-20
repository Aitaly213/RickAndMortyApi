package com.example.rickandmortyapi

import com.example.rickandmortyapi.models.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface CartoonApiService {

    @GET("character")
    fun fetchCharacters(): Call<BaseResponse>
}