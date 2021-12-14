package com.example.bestcatphotos

import com.example.bestcatphotos.model.CatPhoto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_URL = "https://api.thecatapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(API_URL)
    .build()

interface TheCatApiService {
    //    @GET("images/search?limit=20") //api_key=d116de55-877e-4a88-846c-8937466a4af8
//    suspend fun getPhotos(): List<CatPhoto>
    @GET("images/search")
    suspend fun getPhotos(
        @Query("limit") limit: String
    ): List<CatPhoto>
}

object CatApi {
    val retrofitService: TheCatApiService by lazy { retrofit.create(TheCatApiService::class.java) }
}