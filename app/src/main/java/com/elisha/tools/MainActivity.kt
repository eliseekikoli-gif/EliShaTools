package com.elisha.tools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elisha.tools.ui.navigation.Screen
import com.elisha.tools.ui.screens.HomeScreen
import com.elisha.tools.ui.screens.alarms.AlarmScreen
import com.elisha.tools.ui.screens.screenrecord.ScreenRecorderScreen
import com.elisha.tools.ui.screens.settings.SettingsScreen
import com.elisha.tools.ui.screens.whatsapp.StatusSaverScreen
import com.elisha.tools.ui.theme.EliShaToolsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EliShaToolsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) { HomeScreen(navController) }
                    composable(Screen.WhatsApp.route) { StatusSaverScreen(onBack = { navController.popBackStack() }) }
                    composable(Screen.ScreenRecord.route) { ScreenRecorderScreen(onBack = { navController.popBackStack() }) }
                    composable(Screen.Alarms.route) { AlarmScreen(onBack = { navController.popBackStack() }) }
                    composable(Screen.Settings.route) { SettingsScreen(onBack = { navController.popBackStack() }) }
                }
            }
        }
    }
}
