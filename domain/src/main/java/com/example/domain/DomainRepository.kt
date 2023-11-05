package com.example.domain

import com.example.domain.model.Pizza

abstract class DomainRepository {
    abstract suspend fun getPizzas(): List<Pizza>
}