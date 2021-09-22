package com.loens2.autoDynDNS

import java.io.File
import kotlin.system.exitProcess


var IPv4Api = ""
var IPv6Api = ""
var Server = ""
var hostName = ""
var userName = ""
var password = ""

    fun readConfigFile() {
        val configFile = File("./config.properties")
        val config = configFile.readText().split("\n").toMutableList()
        for (i in config.indices) {
            config[i] = config[i].substringAfterLast("=")
        }
        IPv4Api = config[0]
        IPv6Api = config[1]
        Server = config[2]
        hostName = config[3]
        userName = config[4]
        password = config[5]
        println("Loaded config")
    }

    fun createConfigFile() {
        val configFile = File("./config.properties")
        if (!configFile.exists()) {
            configFile.createNewFile()
            println("Created file")
        }
        if (configFile.length() < 50) {
            configFile.writeText("IPv4Api=\nIPv6Api=\nServer=\nhostname=\nusername=\npassword=")
            println("Wrote to file")
            exitProcess(0)
        }
    }

