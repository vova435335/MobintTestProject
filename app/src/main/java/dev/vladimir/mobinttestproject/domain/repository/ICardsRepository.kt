package dev.vladimir.mobinttestproject.domain.repository

import dev.vladimir.mobinttestproject.domain.models.Company

interface ICardsRepository {

    suspend fun getAllCompanies(offset: Int): List<Company>
}
