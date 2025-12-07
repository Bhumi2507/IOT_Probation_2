package com.example.billcalculator.model

data class AppUiModel(
    val itemName : String = "",
    val itemPrice : String = "" ,
    val itemQuantity : String = "" ,
    val subTotal : Double = 0.0,
    val totalAmount : Double = 0.0,
    var showDialog: Boolean = false,
    var paidSwitchCheck : Boolean = false,
)
