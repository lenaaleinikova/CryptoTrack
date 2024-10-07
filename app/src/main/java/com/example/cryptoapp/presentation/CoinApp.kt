package com.example.cryptoapp.presentation

import android.app.Application
import com.example.cryptoapp.di.ApplicationComponent
import dagger.internal.DaggerGenerated

class CoinApp : Application() {

    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
//https://stackoverflow.com/questions/46056421/daggerapplicationcomponent-not-compiled
    }
}
