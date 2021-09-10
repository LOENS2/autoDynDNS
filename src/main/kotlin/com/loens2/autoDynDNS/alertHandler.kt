package com.loens2.autoDynDNS

import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

private val tray = SystemTray.getSystemTray()
private val image = Toolkit.getDefaultToolkit().createImage({}.javaClass.getResource("/trayIcon.png"))
private val trayIcon = TrayIcon(image, "DynDNS Updater")

class alertHandler {

    fun activateOnStartup() {
        trayIcon.isImageAutoSize = true
        tray.add(trayIcon)
    }

    fun setSystemTrayWarning(warning:String) {
        trayIcon.displayMessage("DynDNS Updater", "Update failed", TrayIcon.MessageType.WARNING)
    }
}