package com.example.wallpaperapi.Api

import com.example.wallpaperapi.WallpaperModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface  ApiInterface {

    @GET("search")
    fun getData(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Header("Authorization") auth:String
    ) : retrofit2.Call<WallpaperModel>
}