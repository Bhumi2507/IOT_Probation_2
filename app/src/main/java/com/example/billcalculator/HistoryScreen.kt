package com.example.billcalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)

                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                }
                Spacer( modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)

                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Text(text = "Date : 10/10/2025")
                    Text("Rice : 60")
                    Text("Wheat : 40")
                    Text("Total Price : 100")
                };
            }
        }

    }
}
