package com.example.hm_pizza

import com.example.hm_pizza.di.DaggerHmPizzaDaggerComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class HmPizzaApp:DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerHmPizzaDaggerComponent.builder().bindContext(this).build()
}