package com.example.billcalculator.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel {

    private val _uiState = MutableStateFlow(ItemsModel())
    val uiState : StateFlow<ItemsModel> = _uiState.asStateFlow()

    private var _currentItem by mutableStateOf(ItemsModel())
    var currentItem = _currentItem

    private var _currentList : MutableSet<ItemsModel> = mutableSetOf()
    var currentList = _currentList

    fun updateCurrentItem(
        itemName : String,
        itemPrice : String,
        itemQuantity : String,
    ){
        _currentItem = ItemsModel(itemName,itemPrice,itemQuantity)
        _currentList.add(_currentItem)
        _currentItem = ItemsModel()
    }

    fun updateHistory(
        itemList : List<ItemsModel>
    ){

    }
}