package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class CreateTaskRequestBody(
    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("created_time")
    var createdTime: Long,

    @SerializedName("asigned_to_user_ID")
    var assignedToUserID: Int,

    @SerializedName("priority")
    var priority: Int,

    @SerializedName("deadline")
    var deadline: Long,

    @SerializedName("department_ID")
    var departmentID: Int,

    @SerializedName("status")
    var status: String,

    @SerializedName("progress")
    var progress: Int,
)
