package com.example.eurojackpotnumbergenerator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EuroJackpotContent(
    lastNumbers: String,
    mainNumbers: String,
    euroNumbers: String,
    onGenerate: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Last numbers: $lastNumbers")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Main numbers: $mainNumbers")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Euro numbers: $euroNumbers")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onGenerate) {
            Text("Generate new numbers")
        }
    }
}