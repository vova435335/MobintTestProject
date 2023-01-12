package dev.vladimir.mobinttestproject.domain

import dev.vladimir.mobinttestproject.data.CardsRepository
import javax.inject.Inject

class CardsInteractor @Inject constructor(private val cardsRepository: CardsRepository) {

    suspend fun getAllCompanies(offset: Int): String = cardsRepository.getAllCompanies(offset)
}
