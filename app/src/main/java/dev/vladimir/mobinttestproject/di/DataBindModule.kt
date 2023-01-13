package dev.vladimir.mobinttestproject.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.mobinttestproject.data.repository.CardsRepositoryImpl
import dev.vladimir.mobinttestproject.domain.repository.CardsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataBindModule {

    @Singleton
    @Binds
    fun provideCardsRepository(
        cardsRepositoryImpl: CardsRepositoryImpl
    ): CardsRepository
}