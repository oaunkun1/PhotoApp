package com.ult3mate.photoapp.services

import com.ult3mate.photoapp.modes.JsonResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServies {

    @GET("photos")
    fun getEvents(): Call<List<JsonResult>>

}