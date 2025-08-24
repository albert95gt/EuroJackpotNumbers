package com.example.eurojackpotnumbergenerator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import data.loadNumbers

@Composable
fun EuroJackpotNumbersList(navController: NavController) {
    val context = LocalContext.current
    var lastNumbers by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        lastNumbers = loadNumbers(context)
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(lastNumbers){ lastNumber ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    shape = RoundedCornerShape(23.dp),
                    elevation = CardDefaults.cardElevation(20.dp)
                ){
                    Text(
                        text = lastNumber,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

        }

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Back to home screen")
        }
    }
}