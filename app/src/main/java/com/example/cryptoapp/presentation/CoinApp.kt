package com.example.cryptoapp.presentation

import android.app.Application
import com.example.cryptoapp.di.ApplicationComponent
import com.example.cryptoapp.di.DaggerApplicationComponent
import dagger.internal.DaggerGenerated

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
