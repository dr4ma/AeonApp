package com.dr4ma.domain.models

import com.google.gson.annotations.SerializedName

data class ResponseLoginModel(
    @SerializedName("success")
    val successAuth : Boolean,
    val response: TokenModel? = null,
    val error : ErrorResponseModel? = null
)
