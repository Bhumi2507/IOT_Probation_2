package com.example.billcalculator.model

data class HistoryModel(
    val itemsList : List<ItemsModel> = listOf(),
    val subTotal : String = "",
    val totalBill : String = "",
)
