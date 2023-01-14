package dev.vladimir.mobinttestproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vladimir.mobinttestproject.presentation.common.StringProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideStringProvider(@ApplicationContext context: Context): StringProvider =
        StringProvider(context)
}
