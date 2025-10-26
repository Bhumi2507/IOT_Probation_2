package com.example.billcalculator.model

data class ItemsModel(
    val name : String = "",
    val price : Double = 0.0 ,
    val quantity : Int = 1 ,
    val subTotal : Double = 0.0,
    val totalAmount : Double = 0.0,
    var showDialog: Boolean = false,
    var paidSwitchCheck : Boolean = false,
)
