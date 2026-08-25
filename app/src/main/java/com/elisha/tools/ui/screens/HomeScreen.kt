package com.elisha.tools.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elisha.tools.ui.navigation.Screen
import com.elisha.tools.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "ELI SHA TOOLS", fontSize = 24.sp, fontWeight = FontWeight.Black, color = NeonCyan)
                    Text(text = "Suite Créateur & Gaming", fontSize = 12.sp, color = TextSecondary)
                }
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(Icons.Default.Settings, contentDescription = "Paramètres", tint = TextPrimary)
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HubCard("Statuts WhatsApp", "Sauvegarder photos & vidéos en HD", Icons.Default.Share, NeonGreen) {
                navController.navigate(Screen.WhatsApp.route)
            }
            HubCard("Enregistreur d'Écran", "Capture gameplay 60 FPS avec audio", Icons.Default.PlayArrow, NeonCyan) {
                navController.navigate(Screen.ScreenRecord.route)
            }
            HubCard("Rappels & Alarmes", "Planification précise & notifications", Icons.Default.Notifications, NeonPurple) {
                navController.navigate(Screen.Alarms.route)
            }
        }
    }
}

@Composable
fun HubCard(title: String, subtitle: String, icon: ImageVector, accentColor: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceCard)
            .border(1.dp, SurfaceStroke, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(28.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = subtitle, fontSize = 13.sp, color = TextSecondary)
            }
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = TextSecondary)
        }
    }
}
