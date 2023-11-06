package com.example.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Pizza

@Entity(
    tableName = "PizzaRoomEntity"
)
data class PizzaRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: Int
){
    fun convertToPizza() = Pizza(imgUrl, name, description, price)

    companion object {
        fun convertToPizzaRoomEntity(pizza: Pizza) = pizza.run {
            PizzaRoomEntity(
                null,
                imgUrl,
                name,
                description,
                price
            )
        }
    }
}