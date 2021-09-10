package com.loens2.autoDynDNS

import java.io.File

class configSave {
}
var IPv4Api = ""
var IPv6Api = ""
var Server = ""
var hostName = ""
var userName = ""
var password = ""


fun readConfigFile() {
    val configFile = File("./config.properties")
    var config = configFile.readText().split("\n").toMutableList()
    for (i in config.indices) {
        config[i] = config[i].substringAfterLast("=")
    }
    IPv4Api = config[0]
    IPv6Api = config[1]
    Server = config[3]
    hostName = config[4]
    userName = config[5]
    password = config[6]
}

fun createConfigFile() {
    val configFile = File("./config.properties")
    if (!configFile.exists()) {
        configFile.createNewFile()
    }
    if (configFile.length() < 50) {
        configFile.writeText("IPv4Api=\nIPv6Api=\nServer=\nhostname=\nusername=\npassword=")
    }
}