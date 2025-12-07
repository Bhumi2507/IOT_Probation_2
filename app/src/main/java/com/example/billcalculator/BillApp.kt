package com.example.billcalculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.billcalculator.model.AppViewModel

data class BottomNavItem (
    val route : String,
    val title : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillApp (
    appViewModel : AppViewModel = viewModel()
) {

    val appUiState by appViewModel.uiState.collectAsState()

    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem(
            route = "home",
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            route = "history",
            title = "History",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange
        ),
    )

    Scaffold (
        bottomBar = {
             BottomNavigationBar(items,navController)
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("home"){
                HomeScreen( modifier = Modifier,
                    itemName = appUiState.itemName,
                    onItemNameChange = appViewModel::onItemNameChange,
                    itemPrice = appUiState.itemPrice,
                    onItemPriceChange = appViewModel::onItemPriceChange,
                    itemQuantity = appUiState.itemQuantity,
                    onItemQuantityChange = appViewModel::onItemQuantityChange,
                    onAddNewItemClicked = { appViewModel.onAddNewItem() },
                    onGenerateBill = { appViewModel.generateBill() },
                    showDialog = appUiState.showDialog,
                    subTotal = appUiState.subTotal,
                    totalAmount = appUiState.totalAmount,
                    paidSwitchCheck = appUiState.paidSwitchCheck,
                    onPaidSwitchChange = { appViewModel.paidSwitchUpdate()},
                    )
            }
            composable("history") {
                HistoryScreen(
                    modifier =  Modifier,
                    historyList = appViewModel.itemsList
                    )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items : List<BottomNavItem>,
    navController: NavController
){

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text( item.title ) },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}