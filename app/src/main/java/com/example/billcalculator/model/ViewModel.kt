package com.example.billcalculator.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiModel())
    val uiState : StateFlow<AppUiModel> = _uiState.asStateFlow()

    var itemsList by mutableStateOf(listOf(HistoryModel()))
    var currentItemsList by mutableStateOf(listOf(ItemsModel()))

    fun onItemNameChange ( newItem : String){
        _uiState.update { currentState->
            currentState.copy(
                itemName = newItem
            )
        }
    }

    fun onItemPriceChange ( newPrice : String){
        _uiState.update { currentState->
            currentState.copy(
                itemPrice = newPrice
            )
        }
    }

    fun onItemQuantityChange ( newQuantity : String){
        _uiState.update { currentState->
            currentState.copy(
                itemQuantity = newQuantity
            )
        }
    }

    fun onAddNewItem() {
        updateCurrentList()
        subTotalCalculator()
        _uiState.update { currentState->
            currentState.copy(
                itemName = "",
                itemPrice = "",
                itemQuantity = "",
                subTotal = uiState.value.subTotal,
            )
        }
    }

    fun updateCurrentList(){
        val newItem = ItemsModel(
            itemName = uiState.value.itemName,
            itemPrice = uiState.value.itemPrice,
            itemQuantity = uiState.value.itemQuantity
        )
        currentItemsList = currentItemsList+newItem
    }

    fun generateBill() {
        totalBill()
        _uiState.update { currentState ->
            currentState.copy(
                showDialog = true,
                totalAmount = uiState.value.totalAmount
            )
        }
    }


    fun paidSwitchUpdate() {
        _uiState.update { currentState ->
            currentState.copy(paidSwitchCheck = !currentState.paidSwitchCheck)
        }
        onPaidSwitchCheck()

    }

    private fun onPaidSwitchCheck(){
        val newList = HistoryModel(
            itemsList = currentItemsList,
            subTotal = uiState.value.subTotal.toString(),
            totalBill = uiState.value.totalAmount.toString()
        )
        if(uiState.value.paidSwitchCheck) {
            itemsList = itemsList + newList
        } else {
            currentItemsList = emptyList()
            _uiState.update { currentState ->
                currentState.copy(
                    itemName = "",
                    itemPrice = "",
                    itemQuantity = "",
                    subTotal = 0.0,
                    totalAmount = 0.0,
                    showDialog = false,
                    paidSwitchCheck = false,
                )
            }
        }

    }

    fun subTotalCalculator() {
        val price = _uiState.value.itemPrice.toDoubleOrNull() ?: 0.0
        val quantity = _uiState.value.itemQuantity.toIntOrNull()?:0
        val amount = price*quantity
        val subAmount = _uiState.value.subTotal + amount
        _uiState.update {
            it.copy( subTotal = subAmount )
        }
    }

    fun totalBill() {
        val subtotal = uiState.value.subTotal
        val newItemPrice = uiState.value.itemPrice.toDoubleOrNull()?:0.0
        val newItemQuantity = uiState.value.itemQuantity.toIntOrNull()?:1
        val newItemTotal = newItemPrice*newItemQuantity
        val gst = subtotal * 0.05
        val tip = subtotal * 0.10
        val total = subtotal + newItemTotal + gst + tip

        _uiState.update { it.copy(totalAmount = total) }
    }

    fun reset(){
        _uiState.update { currentState ->
            currentState.copy(
                itemName = "",
                itemPrice = "",
                itemQuantity = "",
                subTotal = 0.0,
                totalAmount = 0.0,
                showDialog = false,
                paidSwitchCheck = false,
            )
        }
        currentItemsList = emptyList()
    }

    init{
        reset()
    }

}





