package com.example.hm_pizza.di.modules

import com.example.hm_pizza.di.modules.presentationModules.FragmentModule
import com.example.hm_pizza.di.modules.presentationModules.ViewModelModule
import dagger.Module

@Module(
    includes = [
        FragmentModule::class,
        ViewModelModule::class,
    ]
)
class PresentationModule