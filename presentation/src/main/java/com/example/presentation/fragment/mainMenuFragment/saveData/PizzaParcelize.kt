package com.example.presentation.fragment.mainMenuFragment.saveData

import android.os.Parcelable
import com.example.domain.model.Pizza
import com.example.domain.model.searchRecipeModel.Hit
import com.example.domain.model.searchRecipeModel.Hits
import kotlinx.parcelize.Parcelize

@Parcelize
data class PizzaParcelize(
    val imgUrl: String,
    val name: String,
    val description: String,
    val price: Int
) : Parcelable {
    fun pizzaParcelizeToPizza() = Pizza(imgUrl, name, description, price)

    companion object {
        fun pizzaToPizzaParcelize(pizza: Pizza):PizzaParcelize = pizza.run {
            PizzaParcelize(imgUrl, name, description, price)
        }
    }
}

@Parcelize
data class ListPizzaParcelize(
    val list: List<PizzaParcelize>
) : Parcelable {
    fun pizzaListParcelizeToListPizza() = list.map { it.pizzaParcelizeToPizza() }

    companion object {
        fun pizzaListToListPizzaParcelize(pizzaList: List<Pizza>):ListPizzaParcelize {
            val result = pizzaList.map { PizzaParcelize.pizzaToPizzaParcelize(it) }
            return ListPizzaParcelize(result)
        }
    }
}