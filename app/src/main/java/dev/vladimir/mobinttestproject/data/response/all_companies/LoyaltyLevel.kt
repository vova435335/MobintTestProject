package dev.vladimir.mobinttestproject.data.response.all_companies

import com.google.gson.annotations.SerializedName

data class LoyaltyLevel (
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
    @SerializedName("requiredSum") val requiredSum: Int,
    @SerializedName("markToCash") val markToCash: Int,
    @SerializedName("cashToMark") val cashToMark: Int
)