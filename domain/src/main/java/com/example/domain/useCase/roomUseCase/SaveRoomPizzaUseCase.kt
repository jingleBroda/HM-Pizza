package com.example.domain.useCase.roomUseCase

import com.example.domain.DomainRepository
import com.example.domain.model.Pizza

class SaveRoomPizzaUseCase(private val repository: DomainRepository) {
    suspend operator fun invoke(pizza: Pizza) = repository.saveRoomPizza(pizza)
}