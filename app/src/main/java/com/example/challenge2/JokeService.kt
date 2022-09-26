package com.example.challenge2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface JokeService {
    @Headers("Accept: application/json")
    @GET("/")
    fun getJoke(@Query("format") format:String): Call<Joke>
}