package com.controlj.tpms.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.Wheel(index: Int) {
    Card(
        modifier = Modifier.weight(1f)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(8.dp)
    )
    {
        Column(
            Modifier.padding(6.dp)
                .fillMaxWidth()
        ) {
            Text("Index $index", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun Live() {
    Column() {
        Row(Modifier.weight(1f)) {
            Wheel(1)
            Wheel(2)
        }
        Row(Modifier.weight(1f)) {
            Wheel(3)
            Wheel(4)
        }
    }
}