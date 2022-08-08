package com.da.retrofitexample.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    companion object{
        val BASE_URL: String = "https://simplifiedcoding.net/demos/"

        fun create() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }


    }

    @GET("marvel")
    suspend  fun getHeroes(): Response<List<Hero>>

    @GET("marvel")
    suspend  fun getHeroesDao(): Response<List<HeroEntity>>

}