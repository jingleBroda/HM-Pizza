package com.example.hm_pizza.di.modules.dataModules

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.dataBase.HmPizzaDataBase
import com.example.hm_pizza.HmPizzaApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun createDataBase(app:HmPizzaApp) = Room.databaseBuilder(
        app,
        HmPizzaDataBase::class.java,
        "HmPizzaDataBase")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesDao(database: HmPizzaDataBase) = database.getDao()
}