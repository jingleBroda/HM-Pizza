package com.example.hm_pizza.di

import com.example.hm_pizza.HmPizzaApp
import com.example.hm_pizza.di.modules.DataModule
import com.example.hm_pizza.di.modules.DomainModule
import com.example.hm_pizza.di.modules.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        PresentationModule::class,
        DataModule::class,
        DomainModule::class,
    ]
)
interface HmPizzaDaggerComponent: AndroidInjector<HmPizzaApp> {
    override fun inject(instance: HmPizzaApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(app:HmPizzaApp): Builder
        fun build(): HmPizzaDaggerComponent
    }
}