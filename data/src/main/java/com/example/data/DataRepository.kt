package com.example.data

import com.example.data.retrofit.HMPizzaRetrofitService
import com.example.data.room.dao.HmPizzaDao
import com.example.data.room.entity.PizzaRoomEntity
import com.example.domain.DomainRepository
import com.example.domain.model.Pizza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val retrofit: HMPizzaRetrofitService,
    private val dao:HmPizzaDao
): DomainRepository() {
    override suspend fun getPizzas(): List<Pizza> {
        val result = retrofit.getRecipe().await()
        val pizzaList = mutableListOf<Pizza>()
        var descriptionPizza = ""
        var imgPizza = ""
        var i = 0
        var namePizza = ""
        result.hits.forEach {
            namePizza = it.recipe.label
            imgPizza = it.recipe.image
            it.recipe.ingredients.forEach { ingredient ->
                i++
                descriptionPizza =
                    if (ingredient.food == null) descriptionPizza + ""
                    else {
                        if(ingredient != it.recipe.ingredients.last()) {
                            descriptionPizza + ingredient.food + ", "
                        }
                        else descriptionPizza + ingredient.food
                    }
            }
            val price = (i * 100) + descriptionPizza.length
            pizzaList.add(
                Pizza(
                    imgPizza,
                    namePizza,
                    descriptionPizza,
                    price
                )
            )
            namePizza = ""
            imgPizza = ""
            descriptionPizza = ""
            i = 0
        }
        return pizzaList
    }

    override suspend fun getRoomPizza(): List<Pizza> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getRoomPizza().map { it.convertToPizza() }
        }
    }

    override suspend fun saveRoomPizza(pizza: Pizza): Long {
        return withContext(Dispatchers.IO) {
            return@withContext dao.saveRoomPizza(PizzaRoomEntity.convertToPizzaRoomEntity(pizza))
        }
    }

    override suspend fun deleteRoomPizza() {
        withContext(Dispatchers.IO) {
            dao.deleteRoomPizza()
        }
    }
}