package com.libreswitch

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rikka.shizuku.Shizuku

// COLORS
val HackerBlack = Color(0xFF0D1117)
val TerminalGreen = Color(0xFF2EA043)
val AlertRed = Color(0xFFDA3633)
val SurfaceGrey = Color(0xFF161B22)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = HackerBlack,
                    surface = SurfaceGrey,
                    primary = TerminalGreen
                )
            ) {
                LibreSwitchApp()
            }
        }
    }
}

@Composable
fun LibreSwitchApp() {
    val context = LocalContext.current
    val packageManager = context.packageManager
    
    val installedProprietary = remember {
        Database.alternatives.filter {
            try { packageManager.getPackageInfo(it.proprietaryPackage, 0); true } 
            catch (e: Exception) { false }
        }
    }
    
    val totalTrackers = Database.alternatives.size
    val foundTrackers = installedProprietary.size
    val privacyScore = ((1.0 - (foundTrackers.toFloat() / totalTrackers.toFloat())) * 100).toInt()

    Column(modifier = Modifier.fillMaxSize().background(HackerBlack).padding(16.dp)) {
        Text("LIBRESWITCH", fontWeight = FontWeight.Black, fontSize = 28.sp, color = TerminalGreen)
        Text("v1.0.0 // PROTOCOL: DEGOOGLE", fontSize = 12.sp, color = Color.Gray)
        
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceGrey),
            modifier = Modifier.fillMaxWidth().height(120.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("INTEGRITY", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("$privacyScore%", fontSize = 48.sp, fontWeight = FontWeight.ExtraBold, color = if(privacyScore > 70) TerminalGreen else AlertRed)
                }
                Icon(
                    imageVector = if(privacyScore > 80) Icons.Default.CheckCircle else Icons.Default.Warning,
                    contentDescription = null,
                    tint = if(privacyScore > 80) TerminalGreen else AlertRed,
                    modifier = Modifier.size(64.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("DETECTED THREATS (${installedProprietary.size})", fontSize = 14.sp, color = TerminalGreen, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        if (installedProprietary.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("SYSTEM CLEAN.", color = Color.Gray)
            }
        } else {
            LazyColumn {
                items(installedProprietary) { app ->
                    AppItemCard(app)
                }
            }
        }
    }
}

@Composable
fun AppItemCard(app: AppMapping) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceGrey),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.background(Color(0xFF21262D), RoundedCornerShape(4.dp)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                    Text(app.category.uppercase(), fontSize = 10.sp, color = Color.LightGray)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(app.proprietaryName, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Replace with ${app.fossName}", color = TerminalGreen, fontSize = 12.sp)
                }
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(app.fossDescription, fontSize = 14.sp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(app.fossUrl))) },
                        colors = ButtonDefaults.buttonColors(containerColor = TerminalGreen),
                        modifier = Modifier.weight(1f)
                    ) { Text("GET FOSS") }
                    
                    Button(
                        onClick = {
                            if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
                                Shizuku.newProcess(arrayOf("pm", "uninstall", "--user", "0", app.proprietaryPackage), null, null)
                            } else {
                                Shizuku.requestPermission(0)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AlertRed),
                        modifier = Modifier.weight(1f)
                    ) { 
                        Icon(Icons.Default.Delete, null, modifier = Modifier.size(16.dp))
                        Text(" UNINSTALL") 
                    }
                }
            }
        }
    }
}
