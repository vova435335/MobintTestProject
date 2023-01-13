package dev.vladimir.mobinttestproject.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.mobinttestproject.data.BASE_URL
import dev.vladimir.mobinttestproject.data.CardsApi
import dev.vladimir.mobinttestproject.data.interceptor.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideCardsApi(retrofit: Retrofit): CardsApi =
        retrofit.create(CardsApi::class.java)
}
