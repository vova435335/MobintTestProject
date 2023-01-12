package dev.vladimir.mobinttestproject.data

import dev.vladimir.mobinttestproject.data.request.AllCompaniesRequestModel
import dev.vladimir.mobinttestproject.data.response.all_companies.AllCompaniesResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardsRepository @Inject constructor(private val cardsApi: CardsApi) {

    suspend fun getAllCompanies(offset: Int): List<AllCompaniesResponseModel> =
        withContext(Dispatchers.IO) {
            cardsApi.getAllCompanies(
                AllCompaniesRequestModel(offset)
            )
        }
}
