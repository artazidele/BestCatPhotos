package com.example.bestcatphotos.model

import com.squareup.moshi.Json

class Message(
    @Json(name = "message") val message: String,
    val id: Int
)