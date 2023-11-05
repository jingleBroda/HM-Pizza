package com.example.domain.model.searchRecipeModel

import com.example.domain.model.searchRecipeModel.recipeInfoConnector.RecipeDoubleString
import com.example.domain.model.searchRecipeModel.recipeInfoConnector.RecipeItemViewInfoConnector

data class NutrientInfo(
    val label:String,
    val quantity:Float,
    val unit:String
): RecipeItemViewInfoConnector {
    override fun getBriefInformation(): RecipeDoubleString =
        RecipeDoubleString(
            label,
            "$quantity $unit"
        )
}