package com.example.billcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.billcalculator.model.HistoryModel
import com.example.billcalculator.model.ItemsModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    historyList : List<HistoryModel>,
    ) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Bill History") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding)
                .padding(8.dp)
        ) {
            items(items = historyList) { item ->
                HistoryCard(
                    itemList = item.itemsList,
                    subTotal = item.subTotal,
                    total = item.totalBill
                )
            }

        }
    }
}

@Composable
fun HistoryCard(
    modifier: Modifier = Modifier,
    itemList : List<ItemsModel>,
    subTotal : String,
    total : String
){
    if (itemList.isNotEmpty()) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                itemList.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item.itemName, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = "₹${item.itemPrice}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "x${item.itemQuantity}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("SubTotal : ")
                    Text(text = "₹${subTotal}")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("TotalBill : ")
                    Text(text = "₹${total}")
                }
            }
        }
    }
}