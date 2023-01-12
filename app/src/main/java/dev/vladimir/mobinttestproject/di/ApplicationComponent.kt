package dev.vladimir.mobinttestproject.di

import dagger.Component
import dev.vladimir.mobinttestproject.di.scopes.ApplicationScope
import retrofit2.Retrofit

@ApplicationScope
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    val retrofit: Retrofit
}
