package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(
    @SerializedName("inserted")
    // 0 vagy 1 | 1 ha sikeres
    var inserted: Int,
) {
    override fun toString(): String {
        return "ForgetPasswordResponse(" +
                "Inserted = '$inserted')"
    }
}