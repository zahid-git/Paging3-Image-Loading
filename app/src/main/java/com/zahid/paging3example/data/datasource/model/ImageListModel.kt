package com.zahid.paging3example.data.datasource.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ImageListModel(
    @SerializedName("id") var id: String? = "",
    @SerializedName("author") var author: String? = "",
    @SerializedName("width") var width: Long? = -1,
    @SerializedName("height") var height: Long? = -1,
    @SerializedName("url") var url: String? = "",
    @SerializedName("download_url") var downloadUrl: String? = "",
)
