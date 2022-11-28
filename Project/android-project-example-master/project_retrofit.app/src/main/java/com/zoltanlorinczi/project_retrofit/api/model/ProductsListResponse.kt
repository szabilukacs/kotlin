package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/23/2021
 */
data class ProductsListResponse(
    @SerializedName("item_count")
    val itemCount: Int,

    @SerializedName("products")
    val products: List<ProductResponse>,

    @SerializedName("timestamp")
    val timeStamp: Long
)