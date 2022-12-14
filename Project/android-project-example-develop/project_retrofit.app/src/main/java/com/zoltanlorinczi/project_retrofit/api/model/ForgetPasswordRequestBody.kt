package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class ForgetPasswordRequestBody(
    @SerializedName("email")
    var email: String,
)