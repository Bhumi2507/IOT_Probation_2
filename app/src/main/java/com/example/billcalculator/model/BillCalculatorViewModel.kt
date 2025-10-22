package com.example.billcalculator.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillCalculatorViewModel {
    private val _currentItemName = MutableStateFlow("")
    private val _currentItemPrice = MutableStateFlow("")
    private val _currentItemQuantity = MutableStateFlow("")

    private val _currentItem = MutableStateFlow(
        Items(
            _currentItemName.value,
            _currentItemPrice.value,
            _currentItemQuantity.value
        )
    )
    val currentItem = _currentItem.asStateFlow()

    private val _itemsList = MutableStateFlow<List<Items>>(emptyList())
    val itemsList = _itemsList.asStateFlow()

}