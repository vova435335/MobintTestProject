package dev.vladimir.mobinttestproject.data.request

import com.google.gson.annotations.SerializedName

data class AllCompaniesRequestModel(
    @SerializedName("offset") val offset: Int,
    @SerializedName("language") val language: String = "json",
)