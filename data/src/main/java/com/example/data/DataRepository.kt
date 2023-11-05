package com.example.data

import com.example.data.retrofit.HMPizzaRetrofitService
import com.example.domain.DomainRepository
import com.example.domain.model.Pizza
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val retrofit: HMPizzaRetrofitService,
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
}