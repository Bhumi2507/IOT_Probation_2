package com.example.billcalculator.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BillCalculatorViewModel {
    private val _currentItemName = MutableStateFlow("")
    private val _currentItemPrice = MutableStateFlow("")
    private val _currentItemQuantity = MutableStateFlow("")

    private val _itemsList = MutableStateFlow<List<Items>>(emptyList())
    val itemsList = _itemsList.asStateFlow()

    fun addItem(){
        val newItem = Items(
            itemName = _currentItemName.value,
            itemPrice = _currentItemPrice.value,
            itemQuantity = _currentItemQuantity.value,
        )
        _itemsList.update { it + newItem }
        clearCurrentItem()
    }

    private fun clearCurrentItem(){
        _currentItemName.value=""
        _currentItemPrice.value=""
        _currentItemQuantity.value=""
    }
}