package com.example.hm_pizza.di.modules.domainModules

import com.example.domain.DomainRepository
import com.example.domain.useCase.GetPizzaUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetPizzaUseCase(repository: DomainRepository) =
        GetPizzaUseCase(repository)
}