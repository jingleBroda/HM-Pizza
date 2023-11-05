package com.example.domain.model.searchRecipeModel

import com.example.domain.model.searchRecipeModel.recipeInfoConnector.RecipeDoubleString
import com.example.domain.model.searchRecipeModel.recipeInfoConnector.RecipeItemViewInfoConnector

data class Ingredient(
    val foodId:String,
    val quantity:Float,
    val measure:String?,
    val weight:Float,
    val food: String?,
    val foodCategory:String?,
): RecipeItemViewInfoConnector {
    override fun getBriefInformation(): RecipeDoubleString =
        RecipeDoubleString(
            food?:"",
            "$quantity $measure"
        )
}