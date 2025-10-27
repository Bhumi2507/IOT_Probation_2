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

    fun updateCurrentItem(
        itemName : String,
        itemPrice : Double,
        itemQuantity : Int,
        subtotal: Double,
    ){
        _uiState.update { currentState->
            currentState.copy(
                name = itemName,
                price = itemPrice,
                quantity = itemQuantity,
                subTotal = subtotal,
            )
        }
        _currentItem = ItemsModel(itemName,itemPrice,itemQuantity)
        _currentList.value=_currentList.value+_currentItem

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

}