package dev.vladimir.mobinttestproject.data.repository

import dev.vladimir.mobinttestproject.data.CardsApi
import dev.vladimir.mobinttestproject.data.mappers.CompaniesMapper
import dev.vladimir.mobinttestproject.data.request.AllCompaniesRequestModel
import dev.vladimir.mobinttestproject.domain.models.Company
import dev.vladimir.mobinttestproject.domain.repository.ICardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardsRepository @Inject constructor(
    private val cardsApi: CardsApi,
    private val companiesMapper: CompaniesMapper,
) : ICardsRepository {


    override suspend fun getAllCompanies(offset: Int): List<Company> =
        withContext(Dispatchers.IO) {
            companiesMapper.mapCompanies(
                cardsApi.getAllCompanies(AllCompaniesRequestModel(offset))
            )
        }
}
