package com.example.pizzaorderingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pizzaorderingapp.ui.theme.CheckoutScreen
import com.example.pizzaorderingapp.ui.theme.PizzaOrderingAppTheme
import com.example.pizzaorderingapp.ui.theme.PizzaSelectionScreen
import com.example.pizzaorderingapp.ui.theme.ToppingSelectionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaOrderingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "pizzaselection") {
                        composable("pizzaselection") {
                            PizzaSelectionScreen(
                                modifier = Modifier.padding(innerPadding)
                            ) { pizza ->
                                it
                                navController.navigate("topping/$pizza")
                            }
                        }
                        composable(
                            "topping/{pizzaId}",
                            arguments = listOf(navArgument("pizzaId"){
                                type = NavType.IntType
                            })
                        ) {
                            ToppingSelectionScreen(
                                modifier = Modifier.padding(innerPadding),
                                pizzaId = it.arguments?.getInt("pizzaId") ?: 0
                            )
                        }
                        composable("checkout") {
                            CheckoutScreen(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PizzaSelectionPreview() {
    PizzaSelectionScreen() {

    }
}

@Preview(showSystemUi = true)
@Composable
private fun ToppingSelectionPreview() {
    ToppingSelectionScreen(pizzaId = 0)
}

@Preview
@Composable
private fun CheckoutPreview() {
    CheckoutScreen()
}
