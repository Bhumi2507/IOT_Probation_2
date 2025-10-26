
package com.example.billcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billcalculator.model.AppViewModel
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(),
) {

    val calculatorUiState by appViewModel.uiState.collectAsState()

    val subTotal = calculateSubTotal(
        subtotal = calculatorUiState.subTotal ,
        price = calculatorUiState.price,
        quantity = calculatorUiState.quantity)
    val totalAmount = totalBill(calculatorUiState.subTotal)

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
                value = appViewModel.itemName,
                onValueChange = { appViewModel.onItemNameChange(it) },
                label = "Item Name",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            EditTextField(
                value = appViewModel.itemPrice,
                onValueChange = { appViewModel.onItemPriceChange(it) },
                label = "Item Price",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            EditTextField(
                value = appViewModel.itemQuantity,
                onValueChange = { appViewModel.onItemQuantityChange(it) },
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

            Button(
                onClick = {
                    appViewModel.updateCurrentItem(
                        appViewModel.itemName,
                        appViewModel.itemPrice.toDoubleOrNull()?:0.0,
                        appViewModel.itemQuantity.toIntOrNull()?:0,
                        subtotal = subTotal,
                    )
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                    Text(
                        "Add New Item",
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            ElevatedButton(
                onClick = { appViewModel.showBillDialog() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text("Generate Bill")
            }
            if(calculatorUiState.showDialog){
                DisplayTotal(R.string.total_bill,totalAmount)
            }

            PaidSwitch(
                switchCheck = calculatorUiState.paidSwitchCheck,
                onCheckChange = {
                    appViewModel.paidSwitchUpdate()
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


private fun calculateSubTotal(
    subtotal: Double,
    price : Double,
    quantity : Int,
) : Double {
    val totalPrice = price*quantity
    val total = subtotal+totalPrice

    return total
}


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
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            "Paid",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(12.dp)
        )
        Switch(
            checked = switchCheck,
            onCheckedChange = onCheckChange,
        )
    }
}