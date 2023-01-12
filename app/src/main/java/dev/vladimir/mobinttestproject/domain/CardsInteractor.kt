package dev.vladimir.mobinttestproject.domain

import dev.vladimir.mobinttestproject.data.CardsRepository
import dev.vladimir.mobinttestproject.data.models.Result
import dev.vladimir.mobinttestproject.domain.models.Company
import javax.inject.Inject

class CardsInteractor @Inject constructor(private val cardsRepository: CardsRepository) {

    suspend fun getAllCompanies(offset: Int): Result<List<Company>> =
        cardsRepository.getAllCompanies(offset)
}
