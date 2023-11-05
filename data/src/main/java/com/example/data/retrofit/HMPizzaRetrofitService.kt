package com.example.data.retrofit

import com.example.domain.model.searchRecipeModel.Hits
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface HMPizzaRetrofitService {
    @GET("https://api.edamam.com/search")
    fun getRecipe(
        @Query("q") nameRecipe:String = "Pizza",
        @Query("calories") caloriesString:String? = null,
        @Query("diet") dietString: Array<String>? = null,
        @Query("from") from:Int = 0,
        @Query("to") to:Int = 5,
    ): Deferred<Hits>
}