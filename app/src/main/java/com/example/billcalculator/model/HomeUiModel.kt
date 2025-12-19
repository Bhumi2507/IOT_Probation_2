package com.example.billcalculator.model

data class HomeUiModel(
    val itemName : String = "",
    val itemPrice : String = "" ,
    val itemQuantity : String = "" ,
    val subTotal : Double = 0.0,
    val totalAmount : Double = 0.0,
    val showDialog: Boolean = false,
    val paidSwitchCheck : Boolean = false,
)
