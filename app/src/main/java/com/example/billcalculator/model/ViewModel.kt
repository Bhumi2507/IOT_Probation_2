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

    private val _uiState = MutableStateFlow(ItemsModel())
    val uiState : StateFlow<ItemsModel> = _uiState.asStateFlow()

    var itemName by mutableStateOf("")
    var itemPrice by mutableStateOf("")
    var itemQuantity by mutableStateOf("")

    private lateinit var _currentItem : ItemsModel

    private var _currentList = MutableStateFlow<List<ItemsModel>>(emptyList())
    var currentList : StateFlow<List<ItemsModel>> = _currentList.asStateFlow()

    fun updateCurrentItem(name: String, price: Double, quantity: Int) {
        val subtotal = price * quantity

        _currentItem = ItemsModel(name, price, quantity, subtotal)
        _currentList.value = _currentList.value + _currentItem

        // Update UI subtotal total (sum of all items)
        val totalSubtotal = _currentList.value.sumOf { it.subTotal }
        _uiState.update { it.copy(subTotal = totalSubtotal) }

        clearInputFields()
    }

    fun onItemNameChange(newValue: String) {
        itemName = newValue
    }

    fun onItemPriceChange(newValue: String) {
        itemPrice = newValue
    }

    fun onItemQuantityChange(newValue: String) {
        itemQuantity = newValue
    }

    private fun clearInputFields(){
        itemName = ""
        itemPrice = ""
        itemQuantity = ""
    }


    fun clearCurrentList(){
        _currentList.value=emptyList()
    }

    fun showBillDialog(){
        _uiState.update { currentDialogState->
            currentDialogState.copy(
                showDialog = true,
            )
        }
    }

    fun paidSwitchUpdate() {
        _uiState.update { currentState ->
            currentState.copy(paidSwitchCheck = !currentState.paidSwitchCheck)
        }
    }

    fun totalBill() {
        val subtotal = uiState.value.subTotal
        val gst = subtotal * 0.05
        val tip = subtotal * 0.10
        val total = subtotal + gst + tip

        _uiState.update { it.copy(totalAmount = total) }
    }
}

