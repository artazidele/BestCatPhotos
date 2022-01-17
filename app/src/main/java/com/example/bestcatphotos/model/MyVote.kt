package com.example.bestcatphotos.model

import com.squareup.moshi.Json

data class MyVote(
    val id: Long,
    @Json(name = "image_id") val imageId: String,
    @Json(name = "sub_id") val subId: String,
    val value: Int
)