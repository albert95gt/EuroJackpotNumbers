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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eurojackpotnumbergenerator.utils.generateSportkaNumbers
import data.saveSportkaNumbers
import kotlinx.coroutines.launch

@Composable
fun SportkaScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var sportkaNumber by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Sportka",
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
            Text(
                text = "New numbers: $sportkaNumber",
                modifier = Modifier.padding(16.dp)
                )
        }

        Button(
            onClick = {
                val numbers = generateSportkaNumbers().joinToString(", ")
                sportkaNumber = numbers

                coroutineScope.launch {
                    saveSportkaNumbers(context, sportkaNumber)
                }
            }
        ) {
            Text("Generate numbers")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("sportka_history")
            }
        ) {
            Text(text = "View history")
        }
    }
}