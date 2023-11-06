package com.example.domain.useCase.roomUseCase

import com.example.domain.DomainRepository
import com.example.domain.model.Pizza

class DeleteRoomPizzaUseCase(private val repository: DomainRepository) {
    suspend operator fun invoke() = repository.deleteRoomPizza()
}