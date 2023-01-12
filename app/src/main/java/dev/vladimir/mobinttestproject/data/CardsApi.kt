package dev.vladimir.mobinttestproject.data

import dev.vladimir.mobinttestproject.data.request.AllCompaniesRequestModel
import retrofit2.http.Body
import retrofit2.http.POST

interface CardsApi {

    @POST("mobileapp/getAllCompanies")
    suspend fun getAllCompanies(
        @Body body: AllCompaniesRequestModel
    ): String
}
