package com.example.billcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var itemName by rememberSaveable { mutableStateOf("") }
    var itemPrice by rememberSaveable { mutableStateOf("") }
    var itemQuantity by rememberSaveable { mutableStateOf("") }

    val price = itemPrice.toDoubleOrNull()?:0.0
    val quantity = itemQuantity.toIntOrNull()?:1

    val subTotal = calculateSubTotal(price,quantity)

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
                text = stringResource(R.string.sub_total,subTotal),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            ElevatedButton(
                onClick = {/*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text("Generate Bill")
            }

        }
    }
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
    price : Double,
    quantity : Int,
) : String {
    val total = price*quantity

    return NumberFormat.getCurrencyInstance().format(total)
}

