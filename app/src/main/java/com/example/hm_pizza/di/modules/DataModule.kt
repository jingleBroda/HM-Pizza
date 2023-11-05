package com.example.hm_pizza.di.modules

import com.example.hm_pizza.di.modules.dataModules.RepositoryModule
import com.example.hm_pizza.di.modules.dataModules.RetrofitModule
import dagger.Module

@Module(
    includes = [
        RetrofitModule::class,
        RepositoryModule::class,
    ]
)
class DataModule