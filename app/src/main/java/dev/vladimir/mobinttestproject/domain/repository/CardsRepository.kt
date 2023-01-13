package dev.vladimir.mobinttestproject.domain.repository

import androidx.paging.PagingData
import dev.vladimir.mobinttestproject.domain.models.Company
import kotlinx.coroutines.flow.Flow

interface CardsRepository {

    fun getPagingAllCompanies(): Flow<PagingData<Company>>
}
