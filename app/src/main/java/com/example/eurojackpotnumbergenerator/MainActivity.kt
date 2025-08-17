package com.example.eurojackpotnumbergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eurojackpotnumbergenerator.ui.theme.EuroJackpotNumberGeneratorTheme
import data.loadLastNumbers
import data.saveLastNumbers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EuroJackpotNumberGeneratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EuroJackpotNumberGenerator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EuroJackpotNumberGenerator(modifier: Modifier){
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
    Column (
        modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Column (
                modifier= Modifier
                    .padding(40.dp)
            ){
                Text(
                    text = "Euro jackpot number generator",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "Last numbers: $lastNumbers",
                )

                Spacer(
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = "Main numbers: $mainNumbers",
                )

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Euro numbers: $euroNumbers",
                )

                Spacer(modifier = Modifier.size(20.dp))

                Button(
                    onClick = {
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
                ) {
                    Text(
                        text = "Generate new numbers"
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Column {
                Text(
                    text = "Sportka number generator",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Generated numbers: $sportkaNumbers"
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        var newNumbers = generateSportkaNumbers().joinToString(",")
                        sportkaNumbers = newNumbers
                    }
                ) {
                    Text(text = "Generate new numbers")
                }
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EuroJackpotNumberGeneratorTheme {
        EuroJackpotNumberGenerator(modifier = Modifier)
    }
}

fun generateMainNumbers(): List<Int>{
    return (1..50).shuffled().take(5).sorted()
}

fun generateEuroNumbers(): List<Int>{
    return (1..12).shuffled().take(2).sorted()
}

fun generateSportkaNumbers(): List<Int>{
    return (1 .. 49).shuffled().take(6).sorted()
}