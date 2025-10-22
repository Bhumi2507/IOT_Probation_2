package com.example.billcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Bill History")},
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
    ){ innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding)
                .padding(8.dp)
            ) {
            item {
                HistoryCard()
                HistoryCard()
                HistoryCard()
                HistoryCard()
                HistoryCard()
            }
        }

    }
}

@Composable
fun HistoryCard(
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.onPrimaryContainer, disabledContainerColor = MaterialTheme.colorScheme.primary, disabledContentColor = MaterialTheme.colorScheme.onPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column (
            modifier = modifier.padding(16.dp)
        ){
            Text(text = "Date : 10/10/2025")
            Text("Rice : 60")
            Text("Wheat : 40")
            Text("Total Price : 100")
        }
    }
}