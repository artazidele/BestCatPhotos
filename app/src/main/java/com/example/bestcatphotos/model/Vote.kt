package com.example.bestcatphotos.model

import com.squareup.moshi.Json


data class Vote (
    @Json(name = "image_id") val image_id: String,
    @Json(name = "sub_id") val sub_id: String,
    @Json(name = "value") val value: Int
)
