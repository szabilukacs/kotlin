package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class GetActivitiesRequestBody(
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("limit")
    var limit: Int
)