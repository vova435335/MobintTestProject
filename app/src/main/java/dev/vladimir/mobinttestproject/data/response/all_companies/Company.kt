package dev.vladimir.mobinttestproject.data.response.all_companies

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("companyId") val companyId: String,
)