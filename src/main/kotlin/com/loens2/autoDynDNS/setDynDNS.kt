package com.loens2.autoDynDNS

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*



class setDynDNS {

    var Server = configSave().getServer()
    var hostName = configSave().getHostMame()
    var userName = configSave().getUserName()
    var password = configSave().getPassword()

    fun updateDynDNS(IPv4: String, IPv6: String) {
        val urlConnect = URL("$Server?hostname=$hostName&myip=$IPv4,$IPv6").openConnection()
        val userPass = "$userName:$password"
        val basicAuth = "Basic " + String(Base64.getEncoder().encode(userPass.toByteArray()))
        urlConnect.setRequestProperty("Authorization", basicAuth)
        val content = BufferedReader(InputStreamReader(urlConnect.getInputStream())).readText()

        if (Regex(content).containsMatchIn("badauth")) {
            alertHandler().setSystemTrayWarning("Wrong DynDNS Credentials!")
        }

        if (Regex(content).containsMatchIn("notfqdn")) {
            alertHandler().setSystemTrayWarning("Wrong hostname!")
        }

        if (Regex(content).containsMatchIn("nohost")) {
            alertHandler().setSystemTrayWarning("Hostname doesn't match the credentials!")
        }

        if (Regex(content).containsMatchIn("numhost")) {
            alertHandler().setSystemTrayWarning("Too many hosts to be updated (more than 20)")
        }

        if (Regex(content).containsMatchIn("abuse")) {
            alertHandler().setSystemTrayWarning("Hostname is blocked for abuse (Too many updates in short time period)")
        }
    }
}




