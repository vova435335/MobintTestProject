package dev.vladimir.mobinttestproject.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.vladimir.mobinttestproject.App
import dev.vladimir.mobinttestproject.R
import dev.vladimir.mobinttestproject.di.DaggerActivityComponent
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerActivityComponent.builder()
            .applicationComponent((application as App).appComponent)
            .build()
            .inject(this)
    }
}
