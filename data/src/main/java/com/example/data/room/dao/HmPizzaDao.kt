package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.room.entity.PizzaRoomEntity

@Dao
interface HmPizzaDao {
    @Query("SELECT * FROM PizzaRoomEntity")
    fun getRoomPizza(): List<PizzaRoomEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRoomPizza(pizza: PizzaRoomEntity): Long
    @Query("DELETE  FROM PizzaRoomEntity")
    fun deleteRoomPizza()
}