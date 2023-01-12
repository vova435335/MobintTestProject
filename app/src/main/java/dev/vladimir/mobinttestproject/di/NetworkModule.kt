package dev.vladimir.mobinttestproject.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dev.vladimir.mobinttestproject.data.BASE_URL
import dev.vladimir.mobinttestproject.di.scopes.ApplicationScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @ApplicationScope
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
