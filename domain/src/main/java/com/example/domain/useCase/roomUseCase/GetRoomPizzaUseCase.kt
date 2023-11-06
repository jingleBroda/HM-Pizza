package com.example.domain.useCase.roomUseCase

import com.example.domain.DomainRepository

class GetRoomPizzaUseCase(private val repository: DomainRepository) {
    suspend operator fun invoke() = repository.getRoomPizza()
}