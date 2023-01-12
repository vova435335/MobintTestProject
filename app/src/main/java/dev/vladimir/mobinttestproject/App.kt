package dev.vladimir.mobinttestproject

import android.app.Application
import dev.vladimir.mobinttestproject.di.DaggerApplicationComponent

class App : Application() {

    val appComponent by lazy {
        DaggerApplicationComponent.builder().build()
    }
}
