package com.elisha.tools.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object WhatsApp : Screen("whatsapp_saver")
    object ScreenRecord : Screen("screen_record")
    object Alarms : Screen("alarms")
    object Settings : Screen("settings")
}
