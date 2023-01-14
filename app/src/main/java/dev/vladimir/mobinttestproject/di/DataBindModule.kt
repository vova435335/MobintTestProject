package dev.vladimir.mobinttestproject.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.mobinttestproject.data.repository.CardsRepository
import dev.vladimir.mobinttestproject.domain.repository.ICardsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataBindModule {

    @Singleton
    @Binds
    fun provideCardsRepository(
        cardsRepository: CardsRepository
    ): ICardsRepository
}