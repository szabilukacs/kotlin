package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

class GetActivitiesResponse(
    @SerializedName("ID")
    var id: Int,

    @SerializedName("type")
    var type: Int,

    @SerializedName("created_by_user_id")
    var created_by_user_id: Int,

    @SerializedName("sub_type")
    var sub_type: Int,

    @SerializedName("created_time")
    var created_time: Long,

    @SerializedName("sub_ID")
    var sub_ID: Int,

    @SerializedName("sub_user_ID")
    var sub_user_ID: Int,

    @SerializedName("note")
    var note: Int,

    @SerializedName("progress")
    var progress: Int,
) {
    override fun toString(): String {
        return "MyUserResponse(" +
                "id=$id, " +
                "created_by_user_id =$created_by_user_id, " +
                "sub_type = $sub_type, " +
                "created_time = $created_time, " +
                "type = $type, " +
                "sub_ID = $sub_ID, " +
                "sub_user_ID = $sub_user_ID, " +
                "note = $note, " +
                "progress = $progress " + " )"
    }

}