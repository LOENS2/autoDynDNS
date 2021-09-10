package com.loens2.autoDynDNS

import java.net.URL
import java.util.*



class setDynDNS {

    var Server = configSave().getServer()
    var hostName = configSave().getHostMame()
    var userName = configSave().getUserName()
    var password = configSave().getPassword()

    fun updateDynDNS(IPv4:String, IPv6:String) {
        val urlConnect = URL("$Server?hostname=$hostName&myip=$IPv4,$IPv6").openConnection()
        val userPass = "$userName:$password"
        val basicAuth = "Basic " + String(Base64.getEncoder().encode(userPass.toByteArray()))
        urlConnect.setRequestProperty("Authorization", basicAuth)
        println("Dyn DNS Update!")
        println(urlConnect.getInputStream().toString())
    }
}



