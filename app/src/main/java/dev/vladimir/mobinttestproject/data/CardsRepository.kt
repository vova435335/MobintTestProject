package dev.vladimir.mobinttestproject.data

import dev.vladimir.mobinttestproject.data.request.AllCompaniesRequestModel
import javax.inject.Inject

class CardsRepository @Inject constructor(private val cardsApi: CardsApi) {

    suspend fun getAllCompanies(offset: Int): String = cardsApi.getAllCompanies(
        AllCompaniesRequestModel(offset)
    )
}
