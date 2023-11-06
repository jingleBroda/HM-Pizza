package com.example.domain.useCase.retrofitUseCase

import com.example.domain.DomainRepository
import com.example.domain.model.Pizza

class GetPizzaUseCase(private val repository: DomainRepository) {
    suspend operator fun invoke(): List<Pizza> = repository.getPizzas()
}