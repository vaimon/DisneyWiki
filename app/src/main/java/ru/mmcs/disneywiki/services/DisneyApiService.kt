package ru.mmcs.disneywiki.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mmcs.disneywiki.entities.DisneyCharacter
import ru.mmcs.disneywiki.entities.DisneyQueryResult


interface DisneyApiService {

    @GET("character")
    fun getCharacters(@Query("page") page: Int = 1): Call<DisneyQueryResult>?

    companion object{
        public fun create() : DisneyApiService {
            return Retrofit.Builder()
                .baseUrl("https://api.disneyapi.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DisneyApiService::class.java)
        }
    }
}