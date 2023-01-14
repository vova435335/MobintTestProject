package dev.vladimir.mobinttestproject.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.vladimir.mobinttestproject.data.paging.CardsPagingSource
import dev.vladimir.mobinttestproject.domain.models.Company
import dev.vladimir.mobinttestproject.domain.repository.ICardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 5

class CardsInteractor @Inject constructor(private val ICardsRepository: ICardsRepository) {

    fun getPagingAllCompanies(): Flow<PagingData<Company>> {
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

    private suspend fun getAllCompanies(offset: Int) =
        ICardsRepository.getAllCompanies(offset)
}
