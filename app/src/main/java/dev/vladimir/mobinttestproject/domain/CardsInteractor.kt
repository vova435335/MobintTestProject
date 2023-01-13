package dev.vladimir.mobinttestproject.domain

import androidx.paging.PagingData
import dev.vladimir.mobinttestproject.domain.models.Company
import dev.vladimir.mobinttestproject.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardsInteractor @Inject constructor(private val cardsRepository: CardsRepository) {

    fun getAllCompanies(): Flow<PagingData<Company>> =
        cardsRepository.getPagingAllCompanies()
}
