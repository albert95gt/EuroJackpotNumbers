package com.example.eurojackpotnumbergenerator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eurojackpotnumbergenerator.utils.generateEuroNumbers
import com.example.eurojackpotnumbergenerator.utils.generateMainNumbers
import data.saveNumbers
import kotlinx.coroutines.launch

@Composable
fun EuroJackpotScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var mainNumbers by remember { mutableStateOf("") }
    var euroNumbers by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "EuroJackpot",
            modifier = Modifier.padding(8.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(20.dp)
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Main numbers: $mainNumbers")
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Euro numbers: $euroNumbers")
            }

        }

        Button(
            onClick = {
                val main = generateMainNumbers().joinToString(", ")
                val euro = generateEuroNumbers().joinToString (", ")
                mainNumbers = main
                euroNumbers = euro

                val combinedNumbers = "Main: $main\nEuro: $euro"

                coroutineScope.launch {
                    saveNumbers(context, combinedNumbers)
                }
            }
        ) {
            Text(text = "Generate new numbers")
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("euro_history")
            }
        ) {
            Text(text = "View history")
        }
    }

}