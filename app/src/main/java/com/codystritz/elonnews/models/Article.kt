package com.codystritz.elonnews.models


import com.google.gson.annotations.SerializedName

data class Article(
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)