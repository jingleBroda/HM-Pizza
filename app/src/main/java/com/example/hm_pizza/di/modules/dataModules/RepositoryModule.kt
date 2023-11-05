package com.example.hm_pizza.di.modules.dataModules

import com.example.data.DataRepository
import com.example.data.retrofit.HMPizzaRetrofitService
import com.example.domain.DomainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesRepository(
        service: HMPizzaRetrofitService,
    ): DomainRepository =
        DataRepository(service)
}