package com.example.wiprocodingassessment.models


import com.google.gson.annotations.SerializedName

data class FactsModel(
    @SerializedName("rows")
    val rows: ArrayList<Row?>?,
    @SerializedName("title")
    val title: String?
) {
    data class Row(
        @SerializedName("description")
        val description: String?,
        @SerializedName("imageHref")
        val imageHref: String?,
        @SerializedName("title")
        val title: String?
    )
}