package com.controlj.tpms.ui.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Devices() {
    LaunchedEffect(Unit) {
       // retrieve
    }
    Column(
        Modifier.fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {

    }
}