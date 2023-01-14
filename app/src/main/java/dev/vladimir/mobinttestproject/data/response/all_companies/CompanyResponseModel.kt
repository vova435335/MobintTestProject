package dev.vladimir.mobinttestproject.data.response.all_companies

import com.google.gson.annotations.SerializedName

data class CompanyResponseModel(
    @SerializedName("company") val company: Company,
    @SerializedName("customerMarkParameters") val customerMarkParameters: CustomerMarkParameters,
    @SerializedName("mobileAppDashboard") val mobileAppDashboard: MobileAppDashboard
)