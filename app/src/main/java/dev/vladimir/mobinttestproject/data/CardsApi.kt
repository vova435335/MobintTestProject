package dev.vladimir.mobinttestproject.data

import dev.vladimir.mobinttestproject.data.request.AllCompaniesRequestModel
import dev.vladimir.mobinttestproject.data.response.all_companies.AllCompaniesResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface CardsApi {

    @POST("mobileapp/getAllCompanies")
    suspend fun getAllCompanies(
        @Body body: AllCompaniesRequestModel
    ): List<AllCompaniesResponseModel>
}
