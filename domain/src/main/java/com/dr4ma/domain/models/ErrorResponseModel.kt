package com.dr4ma.domain.models

import com.google.gson.annotations.SerializedName

data class ErrorResponseModel(
    val errorCode: Int,
    @SerializedName("error_msg")
    val errorMessage: String
)
