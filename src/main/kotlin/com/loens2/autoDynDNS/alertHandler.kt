package com.loens2.autoDynDNS

import java.awt.*
import java.awt.event.ActionListener
import kotlin.system.exitProcess

private val tray = SystemTray.getSystemTray()
private val image = Toolkit.getDefaultToolkit().createImage({}.javaClass.getResource("/trayIcon.png"))
val popup = PopupMenu()
private val trayIcon = TrayIcon(image, "DynDNS Updater", popup)

class alertHandler {

    fun activateOnStartup() {
        trayIcon.isImageAutoSize = true
        val al = ActionListener { exitProcess(0) }
        val menuItem = MenuItem("Exit")
        menuItem.addActionListener(al)
        popup.add(menuItem)
        tray.add(trayIcon)
    }

    fun setSystemTrayWarning(warning:String) {
        trayIcon.displayMessage("DynDNS Updater", warning, TrayIcon.MessageType.WARNING)
    }

    fun setSystemTrayInfo(info:String) {
        trayIcon.displayMessage("DynDNS Updater", info, TrayIcon.MessageType.INFO)
    }
}