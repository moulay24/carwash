package com.example.carwash.ui

import android.graphics.Color
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carwash.R
import org.w3c.dom.Text

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(selectedTab) { selectedTab = it } }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> MapScreen()  // Écran de la carte
                1 -> ListScreen() // Liste des garages
                2 -> ServicesScreen() // Services
                3 -> ChatScreen()  // Chatbot
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Carwash Khenifra",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        backgroundColor = Color(0xFF2A5CAA)
    )
}

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    BottomNavigation(backgroundColor = Color.White) {
        val items = listOf(
            Pair("Carte", R.drawable.ic_map),
            Pair("Liste", R.drawable.ic_list),
            Pair("Services", R.drawable.ic_services),
            Pair("Chat", R.drawable.ic_chat)
        )

        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.second), contentDescription = item.first) },
                label = { Text(text = item.first) },
                selected = selectedTab == index,
                selectedContentColor = Color(0xFFFFA726),
                unselectedContentColor = Color.Gray,
                onClick = { onTabSelected(index) }
            )
        }
    }
}
