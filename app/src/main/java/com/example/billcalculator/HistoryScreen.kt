package com.example.billcalculator

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            items(items = historyList,
                ) { item ->
                HistoryCard(
                    itemList = item.itemsList,
                    subTotal = item.subTotal,
                    total = item.totalBill,
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
    total : String,
){

    var expand by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expand) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.surfaceContainer
    )

    if (itemList.isNotEmpty()) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            Column(
                modifier = modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
                    .background(color = color)
            ) {
                if (expand) {
                    itemList.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                             Text(
                                 text = item.itemName, style = MaterialTheme.typography.labelLarge,
                                 modifier = Modifier.weight(1f)
                             )
                            Text(
                                text = "₹${item.itemPrice}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(0.5f)
                            )
                            Text(
                                text = "x${item.itemQuantity}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    ShowTotal(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                        subTotal = subTotal,
                        total = total
                    )
                    HistoryIconButton(
                        state = true,
                        onButtonClick = { expand = !expand },
                    )
                } else {
                    ShowTotal(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                        subTotal = subTotal,
                        total = total
                    )
                    HistoryIconButton(
                        state = false,
                        onButtonClick = { expand = !expand}
                    )
                }

            }
        }
    }
}

@Composable
fun ShowTotal(
    modifier: Modifier = Modifier,
    subTotal : String,
    total : String,
){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "SubTotal : ",
                modifier = Modifier.weight(1f)
            )
            Text(text = "₹${subTotal}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "TotalBill : ",
                modifier = Modifier.weight(1f)
            )
            Text(text = "₹${total}")
        }
    }
}

@Composable
fun HistoryIconButton(
    state : Boolean,
    onButtonClick : () -> Unit
){
    IconButton(
        onClick = onButtonClick,
        modifier = Modifier.fillMaxWidth(),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Icon(
            imageVector = if(state) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "Up Arrow"
        )
    }
}