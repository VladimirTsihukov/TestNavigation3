package com.tishukoff.testingnavigation3.feature.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(productId: String) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            text = "Detail screen for product: $productId",
            modifier = Modifier.padding(innerPadding),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
