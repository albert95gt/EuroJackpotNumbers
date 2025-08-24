package com.example.eurojackpotnumbergenerator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.eurojackpotnumbergenerator.utils.generateEuroNumbers
import com.example.eurojackpotnumbergenerator.utils.generateMainNumbers
import com.example.eurojackpotnumbergenerator.utils.generateSportkaNumbers
import data.loadLastNumbers
import data.saveLastNumbers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun EuroJackpotNumberGeneratorScreen(modifier: Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var mainNumbers by remember { mutableStateOf("") }
    var euroNumbers by remember { mutableStateOf("") }
    var lastNumbers by remember { mutableStateOf("") }
    var sportkaNumbers by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        val saved = loadLastNumbers(context)
        saved?.let{
            lastNumbers = it.trim()
        }
    }

    Column (modifier = Modifier.fillMaxSize()){
        EuroJackpotContent(
            lastNumbers = lastNumbers,
            mainNumbers = mainNumbers,
            euroNumbers = euroNumbers,
            onGenerate = {
                val main = generateMainNumbers().joinToString(" ")
                val euro = generateEuroNumbers().joinToString(" ")
                mainNumbers = main
                euroNumbers = euro
                val combined = "Main: $main\nEuro: $euro"
                coroutineScope.launch {
                    saveLastNumbers(context, combined)
                    lastNumbers = combined
                }
            }
        )

        SportkaContent(
            sportkaNumbers = sportkaNumbers,
            onGenerate = {
                sportkaNumbers = generateSportkaNumbers().joinToString(",")
            }
        )
    }
}