package dev.vladimir.mobinttestproject.data

import dev.vladimir.mobinttestproject.data.extansions.map
import dev.vladimir.mobinttestproject.data.extansions.toResult
import dev.vladimir.mobinttestproject.data.mappers.CompaniesMapper
import dev.vladimir.mobinttestproject.data.models.Result
import dev.vladimir.mobinttestproject.data.request.AllCompaniesRequestModel
import dev.vladimir.mobinttestproject.domain.models.Company
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardsRepository @Inject constructor(
    private val cardsApi: CardsApi,
    private val companiesMapper: CompaniesMapper,
) {

    suspend fun getAllCompanies(offset: Int): Result<List<Company>> =
        withContext(Dispatchers.IO) {
            cardsApi.getAllCompanies(AllCompaniesRequestModel(offset)).toResult()
                .map(ifSuccess = {
                    companiesMapper.mapCompanies(it)
                })
        }
}
