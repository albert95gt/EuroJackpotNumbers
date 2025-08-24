package com.example.eurojackpotnumbergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eurojackpotnumbergenerator.components.BottomNavigationBar
import com.example.eurojackpotnumbergenerator.components.EuroJackpotNumbersList
import com.example.eurojackpotnumbergenerator.components.EuroJackpotScreen
import com.example.eurojackpotnumbergenerator.components.SportkaScreen
import com.example.eurojackpotnumbergenerator.ui.theme.EuroJackpotNumberGeneratorTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EuroJackpotNumberGeneratorTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Lottery number generator")
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ){ paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(paddingValues)
                    ){
                        composable("home"){ EuroJackpotScreen(navController) }
                        composable("euro_history"){ EuroJackpotNumbersList(navController) }
                        composable("sportka"){ SportkaScreen(navController) }
                    }
                }
            }
        }
    }
}


