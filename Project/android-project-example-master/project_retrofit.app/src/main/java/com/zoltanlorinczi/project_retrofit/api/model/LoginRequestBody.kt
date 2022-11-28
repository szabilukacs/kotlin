package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
data class LoginRequestBody(
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String
)