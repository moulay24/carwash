package com.example.carwash.ui.theme
package com.example.carwash.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carwash.ui.theme.CarwashTheme

class MainScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarwashTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    // Navigation host
    NavHost(navController = navController, startDestination = "listScreen") {
        composable("listScreen") { ListScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarwashTheme {
        MainNavigation()
    }
}


class MainScreen {
}