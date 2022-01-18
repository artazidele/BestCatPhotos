package com.example.bestcatphotos

import com.example.bestcatphotos.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.*

private const val API_URL = "https://api.thecatapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(API_URL)
    .client(client)
    .build()

interface TheCatApiService {
    @GET("images/search")
    suspend fun getPhotos(
        @Query("limit") limit: String
    ): List<CatPhoto>

    @POST("votes")
    @Headers("content-type: application/json", "x-api-key: 45831cb5-c900-48d4-b21d-b15ce3d1fc51")
    fun makeVote(@Body params: Vote): Call<Message>

    @GET("votes")
    @Headers("x-api-key: 45831cb5-c900-48d4-b21d-b15ce3d1fc51")
    suspend fun getMyVotes(
        @Query("sub_id") subId: String
    ): List<MyVote>

    @DELETE("votes/{vote_id}")
    @Headers("x-api-key: 45831cb5-c900-48d4-b21d-b15ce3d1fc51")
    fun deleteVote(
        @Path("vote_id") voteId: Long
    ): Call<DeletedVoteMessage>

    @GET("images/{image_id}")
    @Headers("x-api-key: 45831cb5-c900-48d4-b21d-b15ce3d1fc51")
    fun getPhotoForUrl(
        @Path("image_id") id: String
    ): Call<PhotoResponse>

}

object CatApi {
    val retrofitService: TheCatApiService by lazy { retrofit.create(TheCatApiService::class.java) }
}