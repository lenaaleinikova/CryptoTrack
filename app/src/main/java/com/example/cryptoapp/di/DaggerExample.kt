package com.example.cryptoapp.di

import dagger.Component
import javax.inject.Inject

class Car @Inject constructor(private var engine: Engine)

class Engine @Inject constructor( private var fuel: Fuel)

class Fuel @Inject constructor(){
    private val fuel = "disel"
}

@Component
interface DaggerComponent {
    fun getCar(): Car
    fun getEngine(): Engine
    fun getFuel(): Fuel
}

private var car: Car =DaggerDaggerComponent.create().getCar()