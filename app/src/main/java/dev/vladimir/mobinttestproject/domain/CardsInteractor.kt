package dev.vladimir.mobinttestproject.domain

import dev.vladimir.mobinttestproject.data.CardsRepository
import dev.vladimir.mobinttestproject.data.response.all_companies.AllCompaniesResponseModel
import javax.inject.Inject

class CardsInteractor @Inject constructor(private val cardsRepository: CardsRepository) {

    suspend fun getAllCompanies(offset: Int): List<AllCompaniesResponseModel> =
        cardsRepository.getAllCompanies(offset)
}
