package com.example.hm_pizza.di.modules

import com.example.hm_pizza.di.modules.domainModules.UseCaseModule
import dagger.Module

@Module(
    includes = [
        UseCaseModule::class
    ]
)
class DomainModule {
}