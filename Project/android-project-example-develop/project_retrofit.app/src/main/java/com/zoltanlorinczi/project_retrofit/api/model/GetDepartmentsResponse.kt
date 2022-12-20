package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class GetDepartmentsResponse(
    @SerializedName("ID")
    var id: Int,

    @SerializedName("name")
    var name: String,
)
