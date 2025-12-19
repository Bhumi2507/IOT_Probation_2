
package com.example.billcalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    itemName: String,
    onItemNameChange : (String) -> Unit,
    itemPrice: String,
    onItemPriceChange : (String) -> Unit,
    itemQuantity : String,
    onItemQuantityChange : (String) -> Unit,
    onAddNewItemClicked : () -> Unit,
    onGenerateBill : () -> Unit,
    showDialog : Boolean,
    subTotal : Double,
    totalAmount : Double,
    paidSwitchCheck : Boolean,
    onPaidSwitchChange : (Boolean) -> Unit,
) {
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
                onValueChange = { onItemNameChange(it) },
                label = "Item Name",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            EditTextField(
                value = itemPrice,
                onValueChange = { onItemPriceChange(it) },
                label = "Item Price",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            EditTextField(
                value = itemQuantity,
                onValueChange = { onItemQuantityChange(it) },
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
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = {
                    onAddNewItemClicked()
                },
            )
            {
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
                onClick = {
                    onGenerateBill()
                }
            ) {
                Text("Generate Bill")
            }
            if(showDialog){
                DisplayTotal(
                    R.string.total_bill,
                    NumberFormat.getCurrencyInstance().format(totalAmount)
                )
            }

            PaidSwitch(
                switchCheck = paidSwitchCheck,
                onCheckChange = {
                    onPaidSwitchChange(it)
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
    Column (modifier = Modifier.padding(8.dp)){
        Text(
            text = stringResource(textID, total),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(Modifier.padding(4.dp))
        Text(
            text = "Total bill is inclusive of 5% GST and 10% tip",
            style = MaterialTheme.typography.bodySmall,
        )
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

