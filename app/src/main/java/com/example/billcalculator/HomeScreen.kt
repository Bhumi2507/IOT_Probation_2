package com.example.billcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
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
                label = "Item Name",
                keyboardType = KeyboardType.Text,
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            )

            EditTextField(
                label = "Item Price",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
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
    label : String,
    keyboardType: KeyboardType,
    modifier : Modifier = Modifier,
){
    TextField(
        value = "",
        onValueChange = {/*TODO*/},
        singleLine = true,
        label = {Text(label)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier

    )
}