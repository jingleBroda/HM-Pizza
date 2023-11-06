package com.example.presentation.fragment.mainMenuFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Pizza
import com.example.domain.useCase.retrofitUseCase.GetPizzaUseCase
import com.example.domain.useCase.roomUseCase.DeleteRoomPizzaUseCase
import com.example.domain.useCase.roomUseCase.GetRoomPizzaUseCase
import com.example.domain.useCase.roomUseCase.SaveRoomPizzaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val getPizzaUseCase: GetPizzaUseCase,
    private val getRoomPizzaUseCase: GetRoomPizzaUseCase,
    private val saveRoomPizzaUseCase: SaveRoomPizzaUseCase,
    private val deleteRoomPizzaUseCase: DeleteRoomPizzaUseCase,
): ViewModel() {
    fun getRetrofitPizza(lambda:(List<Pizza>)->Unit) = viewModelScope.launch(Dispatchers.IO) {
        val result = getPizzaUseCase.invoke()
        withContext(Dispatchers.Main) {
            lambda.invoke(result)
        }
    }
    fun getRoomPizza(lambda:(List<Pizza>)->Unit) = viewModelScope.launch(Dispatchers.IO) {
        val result = getRoomPizzaUseCase.invoke()
        withContext(Dispatchers.Main) {
            lambda.invoke(result)
        }
    }
    fun saveRoomPizza(
        pizza: List<Pizza>
    ) = viewModelScope.launch(Dispatchers.IO) {
        pizza.forEach {
            saveRoomPizzaUseCase.invoke(it)
        }
    }
    fun deleteRoomModel() = viewModelScope.launch(Dispatchers.IO) {
        deleteRoomPizzaUseCase.invoke()
    }
}