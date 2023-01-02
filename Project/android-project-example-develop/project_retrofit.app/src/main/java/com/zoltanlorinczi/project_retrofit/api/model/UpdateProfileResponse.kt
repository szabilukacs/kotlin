package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

class UpdateProfileResponse(
    @SerializedName("message")
    var message: String,
) {
    override fun toString(): String {
        return "updateProfile Response = $message "
    }
}