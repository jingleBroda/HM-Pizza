package com.example.data.room.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.dao.HmPizzaDao
import com.example.data.room.entity.PizzaRoomEntity

@Database(
    version = 1,
    entities = [
        PizzaRoomEntity::class
    ],
    exportSchema = false
)
abstract class HmPizzaDataBase(): RoomDatabase() {
    abstract fun getDao(): HmPizzaDao
}