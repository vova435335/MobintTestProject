package dev.vladimir.mobinttestproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.vladimir.mobinttestproject.data.CardsApi
import dev.vladimir.mobinttestproject.data.mappers.CompaniesMapper
import dev.vladimir.mobinttestproject.data.paging.CardsPagingSource
import dev.vladimir.mobinttestproject.data.request.AllCompaniesRequestModel
import dev.vladimir.mobinttestproject.domain.models.Company
import dev.vladimir.mobinttestproject.domain.repository.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val PAGE_SIZE = 5

class CardsRepositoryImpl @Inject constructor(
    private val cardsApi: CardsApi,
    private val companiesMapper: CompaniesMapper,
) : CardsRepository {

    override fun getPagingAllCompanies(): Flow<PagingData<Company>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CardsPagingSource(
                    loader = { offset ->
                        getAllCompanies(offset)
                    }
                )
            }
        ).flow
    }

    private suspend fun getAllCompanies(offset: Int): List<Company> =
        withContext(Dispatchers.IO) {
            companiesMapper.mapCompanies(
                cardsApi.getAllCompanies(AllCompaniesRequestModel(offset))
            )
        }
}
