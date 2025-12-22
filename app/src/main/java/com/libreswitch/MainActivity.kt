package com.libreswitch

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import rikka.shizuku.Shizuku

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LibreSwitchApp()
            }
        }
    }
}

@Composable
fun LibreSwitchApp() {
    val context = LocalContext.current
    val packageManager = context.packageManager
    
    val matches = remember {
        Database.alternatives.filter { mapping ->
            try {
                packageManager.getPackageInfo(mapping.proprietaryPackage, 0)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "LibreSwitch", 
            style = MaterialTheme.typography.headlineLarge, 
            color = Color(0xFF4CAF50), 
            fontWeight = FontWeight.Bold
        )
        Text("Found ${matches.size} apps to replace.")
        Spacer(modifier = Modifier.height(16.dp))
        
        if (matches.isEmpty()) {
            Text("System clean! No targeted apps found.")
        } else {
            LazyColumn {
                items(matches) { item ->
                    AppItem(item)
                }
            }
        }
    }
}

@Composable
fun AppItem(mapping: AppMapping) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Replace ${mapping.proprietaryName}", fontWeight = FontWeight.Bold)
                    Text("with ${mapping.fossName}", color = Color(0xFF4CAF50))
                }
            }
            
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(mapping.fossDescription, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapping.fossUrl))
                        context.startActivity(intent)
                    }) { Text("Get") }
                    
                    OutlinedButton(onClick = {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.parse("package:${mapping.proprietaryPackage}")
                        context.startActivity(intent)
                    }) { Text("Disable") }
                }
            }
        }
    }
}
