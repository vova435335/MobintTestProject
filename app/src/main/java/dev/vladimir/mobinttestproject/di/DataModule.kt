package dev.vladimir.mobinttestproject.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DataBindModule::class])
@InstallIn(SingletonComponent::class)
class DataModule