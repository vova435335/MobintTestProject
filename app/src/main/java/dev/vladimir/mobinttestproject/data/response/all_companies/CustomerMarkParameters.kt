package dev.vladimir.mobinttestproject.data.response.all_companies

import com.google.gson.annotations.SerializedName

data class CustomerMarkParameters (
    @SerializedName("loyaltyLevel") val loyaltyLevel: LoyaltyLevel,
    @SerializedName("mark") val mark: Int
)