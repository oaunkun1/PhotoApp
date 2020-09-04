package com.ult3mate.photoapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private var retrofit:Retrofit? = null
    fun getClient():Retrofit{
        if (retrofit == null){
            retrofit  =  Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/albums/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
    fun getImageURL() = ""
}