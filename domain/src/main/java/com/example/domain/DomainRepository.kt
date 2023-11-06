package com.example.domain

import com.example.domain.model.Pizza

abstract class DomainRepository {
    abstract suspend fun getPizzas(): List<Pizza>
    abstract suspend fun getRoomPizza(): List<Pizza>
    abstract suspend fun saveRoomPizza(pizza: Pizza): Long
    abstract suspend fun deleteRoomPizza()
}