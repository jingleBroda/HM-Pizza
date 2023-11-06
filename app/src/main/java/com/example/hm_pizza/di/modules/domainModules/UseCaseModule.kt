package com.example.hm_pizza.di.modules.domainModules

import com.example.domain.DomainRepository
import com.example.domain.useCase.retrofitUseCase.GetPizzaUseCase
import com.example.domain.useCase.roomUseCase.DeleteRoomPizzaUseCase
import com.example.domain.useCase.roomUseCase.GetRoomPizzaUseCase
import com.example.domain.useCase.roomUseCase.SaveRoomPizzaUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetPizzaUseCase(repository: DomainRepository) =
        GetPizzaUseCase(repository)
    @Provides
    fun provideDeleteRoomPizzaUseCase(repository: DomainRepository) =
        DeleteRoomPizzaUseCase(repository)
    @Provides
    fun provideGetRoomPizzaUseCase(repository: DomainRepository) =
        GetRoomPizzaUseCase(repository)
    @Provides
    fun provideSaveRoomPizzaUseCase(repository: DomainRepository) =
        SaveRoomPizzaUseCase(repository)
}