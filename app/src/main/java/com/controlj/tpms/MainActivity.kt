package com.controlj.tpms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.controlj.tpms.ui.tabs.Tabs
import com.controlj.tpms.ui.theme.TPMSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TPMSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainTabs()
                }
            }
        }
    }

}

@Composable
fun MainTabs() {
    var selectedIndex by remember { mutableStateOf(0) }
    val currentTab = Tabs.values()[selectedIndex]
    Column {
        TabRow(selectedTabIndex = selectedIndex) {
            Tabs.values().forEachIndexed { index, tab ->
                val selected = selectedIndex == index
                Tab(selected = selected, onClick = {
                    selectedIndex = index
                }, text = {
                    Text(
                        text = tab.name,
                        fontWeight = if (selected) FontWeight.Bold else null
                    )
                }, icon = {
                    Image(
                        painter = tab.drawIcon(),
                        contentDescription = tab.name
                    )
                })
            }
        }
        currentTab.DrawContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TPMSTheme {
        MainTabs()
    }
}