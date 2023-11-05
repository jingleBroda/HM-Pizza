package com.example.domain.model.searchRecipeModel

import com.example.domain.model.searchRecipeModel.Ingredient
import com.example.domain.model.searchRecipeModel.NutrientInfo

data class Recipe(
    val uri:String,
    val label:String,
    val image:String,
    val source:String,
    val url:String,
    val yield:Float,
    val calories:Float,
    val totalWeight:Float,
    val ingredients:List<Ingredient>,
    val totalNutrients:Map<String, NutrientInfo>,
    val totalDaily:Map<String, NutrientInfo>,
    val dietLabels:List<String>,
    val healthLabels:List<String>,
)