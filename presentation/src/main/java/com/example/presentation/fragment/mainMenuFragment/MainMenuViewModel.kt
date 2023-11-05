package com.example.presentation.fragment.mainMenuFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Pizza
import com.example.domain.model.searchRecipeModel.Hits
import com.example.domain.useCase.GetPizzaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val getPizzaUseCase: GetPizzaUseCase
): ViewModel() {
    fun getPizza(lambda:(List<Pizza>)->Unit) = viewModelScope.launch(Dispatchers.IO) {
        val result = getPizzaUseCase.invoke()
        withContext(Dispatchers.Main) {
            lambda.invoke(result)
        }
    }
}