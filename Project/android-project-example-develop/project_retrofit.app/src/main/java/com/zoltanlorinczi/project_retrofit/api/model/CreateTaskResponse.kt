package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class CreateTaskResponse(
    @SerializedName("message")
    var message: String,
) {
    override fun toString(): String {
        return "Create Task Response = $message "
    }
}
