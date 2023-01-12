package dev.vladimir.mobinttestproject.data.response.all_companies

import com.google.gson.annotations.SerializedName

data class MobileAppDashboard (
    @SerializedName("companyName") val companyName: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("backgroundColor") val backgroundColor: String,
    @SerializedName("mainColor") val mainColor: String,
    @SerializedName("cardBackgroundColor") val cardBackgroundColor: String,
    @SerializedName("textColor") val textColor: String,
    @SerializedName("highlightTextColor") val highlightTextColor: String,
    @SerializedName("accentColor") val accentColor: String
)