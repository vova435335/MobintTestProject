package dev.vladimir.mobinttestproject.di

import dagger.Component
import dev.vladimir.mobinttestproject.di.scopes.ActivityScope
import dev.vladimir.mobinttestproject.presentation.MainActivity

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}
