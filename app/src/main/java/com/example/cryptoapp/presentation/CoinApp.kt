package com.example.cryptoapp.presentation

import android.app.Application
import com.example.cryptoapp.di.ApplicationComponent

class CoinApp : Application() {

    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.factory().create(this)
    }
}
