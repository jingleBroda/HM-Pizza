package com.example.hm_pizza.di.modules

import com.example.hm_pizza.di.modules.dataModules.RepositoryModule
import com.example.hm_pizza.di.modules.dataModules.RetrofitModule
import com.example.hm_pizza.di.modules.dataModules.RoomModule
import dagger.Module

@Module(
    includes = [
        RetrofitModule::class,
        RepositoryModule::class,
        RoomModule::class,
    ]
)
class DataModule