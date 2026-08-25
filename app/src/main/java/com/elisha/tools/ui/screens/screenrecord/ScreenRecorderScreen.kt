package com.elisha.tools.ui.screens.screenrecord

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elisha.tools.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRecorderScreen(onBack: () -> Unit) {
    var isRecording by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Enregistreur d'Écran", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Paramètres de capture : 1080p Full HD",
                    color = TextPrimary,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Text(
                text = if (isRecording) "EN COURS" else "PRÊT",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = if (isRecording) AlertRed else NeonCyan
            )
            Button(
                onClick = { isRecording = !isRecording },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (isRecording) AlertRed else NeonCyan)
            ) {
                Text(
                    text = if (isRecording) "Arrêter" else "Démarrer l'enregistrement",
                    color = DarkBackground,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
