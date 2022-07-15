package com.example.shapesapp.models

import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    @SerializedName("textures-patterns")
    val texturesPatterns: TexturesPatterns
)