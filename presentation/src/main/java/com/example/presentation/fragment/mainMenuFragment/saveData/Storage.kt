package com.example.presentation.fragment.mainMenuFragment.saveData

import android.content.Context
import com.example.domain.model.Pizza

//ROOM НЕ ЗАХОТЕЛА ВСТАВАТЬ В ПРОЕКТ
class Storage(context: Context) {
    private val setting = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    private val editor = setting.edit()

    fun savePizzaList(pizza: List<Pizza>) {
        //1
        var stringKeyItemPizza = ""
        val keyList = mutableListOf<String>()
        pizza.forEach {
            keyList.add(it.name)
            stringKeyItemPizza += if(it != pizza.last()) it.name + "@@" else it.name
        }
        editor.putString(KEY_KEY_PIZZA, stringKeyItemPizza)
        //2
        var i = 0
        pizza.forEach {
            editor.putString("${keyList[i]}_imgUrl", it.imgUrl)
            editor.putString("${keyList[i]}_name", it.name)
            editor.putString("${keyList[i]}_description", it.description)
            editor.putInt("${keyList[i]}_price", it.price)
            i++
        }
        editor.commit()
    }

    fun getPizzaList(): List<Pizza> {
        val result = mutableListOf<Pizza>()
        val stringKeyItemPizza = setting.getString(KEY_KEY_PIZZA, null) ?: return emptyList()
        //парсим ключи
        val keyList = stringKeyItemPizza.split("@@")
        //вытаскиваем пиццы
        keyList.forEach {
            val imgUrl = setting.getString("${it}_imgUrl", "") ?: ""
            val name = setting.getString("${it}_name", "") ?: ""
            val description = setting.getString("${it}_description", "") ?: ""
            val price = setting.getInt("${it}_price", 0)
            result.add(
                Pizza(
                    imgUrl,
                    name,
                    description,
                    price,
                )
            )
        }
        return result
    }

    fun clear() = editor.clear()

    companion object {
        const val STORAGE_NAME = "STORAGE_NAME"
        private const val KEY_KEY_PIZZA = "KEY_PIZZA"
    }
}