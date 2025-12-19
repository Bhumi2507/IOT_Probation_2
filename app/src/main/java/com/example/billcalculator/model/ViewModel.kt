package com.example.billcalculator.model

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiModel())
    val homeUiState : StateFlow<HomeUiModel> = _homeUiState.asStateFlow()

    private val _profileUiState = MutableStateFlow(ProfileModel())
    val profileUiState : StateFlow<ProfileModel> = _profileUiState.asStateFlow()

    var itemsList by mutableStateOf(listOf(HistoryModel()))
    var currentItemsList by mutableStateOf(listOf(ItemsModel()))

    fun onItemNameChange ( newItem : String){
        _homeUiState.update { currentState->
            currentState.copy(
                itemName = newItem
            )
        }
    }

    fun onItemPriceChange ( newPrice : String){
        _homeUiState.update { currentState->
            currentState.copy(
                itemPrice = newPrice
            )
        }
    }

    fun onItemQuantityChange ( newQuantity : String){
        _homeUiState.update { currentState->
            currentState.copy(
                itemQuantity = newQuantity
            )
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun onAddNewItem() {
        if (homeUiState.value.itemName.isNotEmpty()||homeUiState.value.itemPrice.isNotEmpty()||homeUiState.value.itemQuantity.isNotEmpty())
        updateCurrentList()
        subTotalCalculator()
        _homeUiState.update { currentState->
            currentState.copy(
                itemName = "",
                itemPrice = "",
                itemQuantity = "",
                subTotal = homeUiState.value.subTotal,
            )
        }
    }

    fun updateCurrentList(){
        val newItem = ItemsModel(
            itemName = homeUiState.value.itemName,
            itemPrice = homeUiState.value.itemPrice,
            itemQuantity = homeUiState.value.itemQuantity
        )
        currentItemsList = currentItemsList+newItem
    }

    fun generateBill() {
        totalBill()
        _homeUiState.update { currentState ->
            currentState.copy(
                showDialog = true,
                totalAmount = homeUiState.value.totalAmount
            )
        }
        if (homeUiState.value.itemName.isNotEmpty() && homeUiState.value.itemPrice.isNotEmpty() && homeUiState.value.itemQuantity.isNotEmpty()) {
            updateCurrentList()
        }
    }


    fun paidSwitchUpdate() {
        if(homeUiState.value.showDialog) {
            _homeUiState.update { currentState ->
                currentState.copy(paidSwitchCheck = !currentState.paidSwitchCheck)
            }
            onPaidSwitchCheck()
        }

    }

    private fun onPaidSwitchCheck(){
        val newList = HistoryModel(
            itemsList = currentItemsList,
            subTotal = homeUiState.value.subTotal.toString(),
            totalBill = homeUiState.value.totalAmount.toString()
        )
        if(homeUiState.value.paidSwitchCheck) {
            itemsList = itemsList + newList
        } else {
            currentItemsList = emptyList()
            _homeUiState.update { currentState ->
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
        val price = _homeUiState.value.itemPrice.toDoubleOrNull() ?: 0.0
        val quantity = _homeUiState.value.itemQuantity.toIntOrNull()?:0
        val amount = price*quantity
        val subAmount = _homeUiState.value.subTotal + amount
        _homeUiState.update {
            it.copy( subTotal = subAmount )
        }
    }

    fun totalBill() {
        val newItemPrice = homeUiState.value.itemPrice.toDoubleOrNull()?:0.0
        val newItemQuantity = homeUiState.value.itemQuantity.toIntOrNull()?:1
        val newItemTotal = newItemPrice*newItemQuantity
        val subtotal = homeUiState.value.subTotal + newItemTotal
        val gst = subtotal * 0.05
        val tip = subtotal * 0.10
        val total = subtotal + gst + tip

        _homeUiState.update {
            it.copy(
                subTotal = subtotal,
                totalAmount = total
            )
        }
    }

    fun onProfileEdit(){

    }

    fun reset(){
        _homeUiState.update { currentState ->
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





