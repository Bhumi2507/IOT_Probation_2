package com.example.billcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.billcalculator.model.items
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {


    var itemName by rememberSaveable { mutableStateOf("") }
    var itemPrice by rememberSaveable { mutableStateOf("") }
    var itemQuantity by rememberSaveable { mutableStateOf("") }

    val price = itemPrice.toDoubleOrNull()?:0.0
    val quantity = itemQuantity.toIntOrNull()?:1

    var subTotal = 0.0
    subTotal = calculateSubTotal(subTotal,price,quantity)

    val totalAmount : String = totalBill(subTotal)

    var showDialog by rememberSaveable { mutableStateOf(false) }

    var paidSwitch by rememberSaveable { mutableStateOf(false) }

    var itemsList = rememberSaveable { mutableStateListOf<items>() }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Generate Bill")},
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(innerPadding)
                .fillMaxSize()
        ) {
            EditTextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = "Item Name",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            EditTextField(
                value = itemPrice,
                onValueChange = { itemPrice = it },
                label = "Item Price",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            EditTextField(
                value = itemQuantity,
                onValueChange = { itemQuantity = it },
                label = "quantity",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Text(
                text = stringResource(R.string.sub_total, NumberFormat.getCurrencyInstance().format(subTotal)),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            ElevatedButton(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text("Generate Bill")
            }
            if(showDialog){
                DisplayTotal(R.string.total_bill,totalAmount)
            }
            PaidSwitch(
                switchCheck = paidSwitch,
                onCheckChange = {
                    
                }
            )

        }
    }
}


@Composable
fun DisplayTotal(
    textID : Int,
    total : String
){
    Text(
        text = stringResource(textID,total),
        style = MaterialTheme.typography.displaySmall
    )
}

@Composable
fun EditTextField(
    value : String,
    onValueChange : (String) -> Unit,
    label : String,
    keyboardOptions: KeyboardOptions,
    modifier : Modifier = Modifier,
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = {Text(label)},
        keyboardOptions = keyboardOptions,
        modifier = modifier

    )
}

@Composable
private fun calculateSubTotal(
    subtotal: Double,
    price : Double,
    quantity : Int,
) : Double {
    val totalPrice = price*quantity
    val total = subtotal+totalPrice

    return total
}

@Composable
private fun totalBill(
    subtotal : Double,
    gst : Double = 5.0,
    tip : Double = 10.0,
) : String {
    val gstAmount = (subtotal*gst)/100
    val tipAmount = (subtotal*tip)/100

    val totalBill = subtotal + gstAmount + tipAmount

    return NumberFormat.getCurrencyInstance().format(totalBill)
}

@Composable
fun PaidSwitch(
    switchCheck : Boolean,
    onCheckChange : (Boolean) -> Unit,
    modifier : Modifier = Modifier
){
    Row(
        modifier = modifier
    ){
        Text("Paid")
        Switch(
            checked = switchCheck,
            onCheckedChange = onCheckChange,
            modifier = Modifier.fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}