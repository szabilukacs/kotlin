package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class CreateTaskRequestBody(
    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

//    @SerializedName("created_time")
//    var createdTime: Long,

    @SerializedName("assignedToUserId")
    var assignedToUserID: Int,

    @SerializedName("priority")
    var priority: Int,

    @SerializedName("deadline")
    var deadline: Long,

    @SerializedName("departmentId")
    var departmentId: Int,

    @SerializedName("status")
    // A Backend csak szamokat ment el
    var status: Int,

    @SerializedName("progress")
    var progress: Int,
)
